package com.example.emp_regtrn.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.emp_regtrn.dto.PincodeFinalResponse;
import com.example.emp_regtrn.dto.PincodeResponse;
import com.example.emp_regtrn.dto.PostOfficeResponse;
import com.example.emp_regtrn.exception.PincodeApiException;
import com.example.emp_regtrn.exception.PincodeNotFoundException;

import tools.jackson.databind.ObjectMapper;


@Service
public class PincodeService {

    // java.net.http.HttpClient uses a different SSL engine than the old
    // HttpURLConnection-based RestTemplate factory, which can avoid TLS
    // handshake resets caused by some antivirus/network TLS inspection.
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${pincode.api.url}")
    private String pincodeApiUrl; // e.g. http://www.postalpincode.in/api/pincode/

    public PincodeFinalResponse getPincodeDetails(String pincode) {

        if (pincode == null || !pincode.matches("^\\d{6}$")) {
            throw new PincodeNotFoundException("Pincode not found");
        }

        String url = pincodeApiUrl + pincode;
        System.out.println(url);
        String responseBody;
      

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();
            System.out.println(request+"request");

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse+"httpResponse");

            if (httpResponse.statusCode() != 200) {
                throw new PincodeApiException(
                        "Unable to fetch pincode details, status: " + httpResponse.statusCode());
            }

            responseBody = httpResponse.body();
            System.out.println(responseBody);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // remove once confirmed working
            throw new PincodeApiException("Unable to fetch pincode details");
        }

        PincodeResponse response;
        try {
            response = objectMapper.readValue(responseBody, PincodeResponse.class);
        } catch (Exception e) {
            e.printStackTrace(); // remove once confirmed working
            throw new PincodeApiException("Unable to parse pincode details");
        }

        if (response == null
                || !"Success".equalsIgnoreCase(response.getStatus())
                || response.getPostOffice() == null
                || response.getPostOffice().isEmpty()) {

            throw new PincodeNotFoundException("Pincode not found");
        }

        List<PostOfficeResponse> offices = response.getPostOffice();
        String city = offices.get(0).getDistrict().trim();
        String state = offices.get(0).getState().trim();
        String country = offices.get(0).getCountry().trim();

        List<String> postOfficeNames = offices.stream()
                .map(PostOfficeResponse::getName)
                .filter(name -> name != null && !name.isBlank())
                .map(String::trim)
                .toList();

        return new PincodeFinalResponse(city, state, country, postOfficeNames);
    }
}