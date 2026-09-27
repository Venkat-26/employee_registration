package com.example.emp_regtrn.service;

import com.example.emp_regtrn.dto.PincodeFinalResponse;
import com.example.emp_regtrn.dto.PincodeResponse;
import com.example.emp_regtrn.dto.PostOfficeResponse;
import com.example.emp_regtrn.exception.PincodeApiException;
import com.example.emp_regtrn.exception.PincodeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@Service
public class PincodeService {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${pincode.api.url}")
    private String pincodeApiUrl;

    public PincodeFinalResponse getPincodeDetails(String pincode) {

        // Validate pincode
        if (pincode == null || !pincode.matches("^\\d{6}$")) {
            throw new PincodeNotFoundException("Pincode not found");
        }

        String url = pincodeApiUrl + pincode;

        String responseBody;

        try {
            // Build HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(15))
                    .header("User-Agent", "Mozilla/5.0")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Call external API
            HttpResponse<String> httpResponse =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            // Check HTTP status
            if (httpResponse.statusCode() != 200) {
                throw new PincodeApiException(
                        "Unable to fetch pincode details, status: "
                                + httpResponse.statusCode()
                );
            }

            responseBody = httpResponse.body();

        } catch (IOException e) {

            throw new PincodeApiException(
                    "Unable to connect to pincode API"
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new PincodeApiException(
                    "Pincode API request was interrupted"
            );
        }

        // Parse response
        PincodeResponse response;

        try {

            response = objectMapper.readValue(
                    responseBody,
                    PincodeResponse.class
            );

        } catch (Exception e) {

            throw new PincodeApiException(
                    "Unable to parse pincode API response"
            );
        }

        // Validate API response
        if (response == null
                || !"Success".equalsIgnoreCase(response.getStatus())
                || response.getPostOffice() == null
                || response.getPostOffice().isEmpty()) {

            throw new PincodeNotFoundException(
                    "Pincode not found"
            );
        }

        // Get post offices
        List<PostOfficeResponse> offices =
                response.getPostOffice();

        // Get city/state/country from first post office
        String city = offices.get(0).getDistrict() != null
                ? offices.get(0).getDistrict().trim()
                : null;

        String state = offices.get(0).getState() != null
                ? offices.get(0).getState().trim()
                : null;

        String country = offices.get(0).getCountry() != null
                ? offices.get(0).getCountry().trim()
                : null;

        // Get all post office names
        List<String> postOfficeNames = offices.stream()
                .map(PostOfficeResponse::getName)
                .filter(name -> name != null && !name.isBlank())
                .map(String::trim)
                .toList();

        return new PincodeFinalResponse(
                city,
                state,
                country,
                postOfficeNames
        );
    }
}