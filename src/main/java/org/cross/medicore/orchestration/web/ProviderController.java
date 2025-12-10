package org.cross.medicore.orchestration.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.orchestration.orchestrator.ProviderOrchestrator;
import org.cross.medicore.orchestration.web.dto.CreateProviderRequest;
import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;
import org.cross.medicore.security.api.dto.UserCredsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services/providers")
public class ProviderController {
    private final ProviderOrchestrator providerOrchestrator;

    @GetMapping("/{providerId}")
    public ResponseEntity<ApiResponseV1<ProviderDetails>>
    getProviderDetails(
                  @PathVariable long providerId,
                  @NonNull HttpServletRequest request){
        ProviderDetails providerDetails = providerOrchestrator.getProviderDetails(providerId);

        ApiResponseV1<ProviderDetails> apiResponse = ApiResponseV1.ok(providerDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseV1<ProviderBriefProfile>>
        createProvider(@RequestBody CreateProviderRequest requestBody,
                      @NonNull HttpServletRequest request){
        CreateProviderDto dto = requestBody.createProviderDto();
        UserCredsDto creds = requestBody.creds();

        ProviderBriefProfile createdProvider = providerOrchestrator.createProvider(dto, creds.username(), creds.password());

        ApiResponseV1<ProviderBriefProfile> apiResponse = ApiResponseV1.created(createdProvider, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }

    @PatchMapping("/{providerId}")
    public ResponseEntity<ApiResponseV1<ProviderDetails>>
    updateProviderDetails(
            @PathVariable long providerId,
            @RequestBody UpdateProviderDetailsDto dto,
            @NonNull HttpServletRequest request){
        ProviderDetails providerDetails = providerOrchestrator.updateProviderDetails(providerId, dto);

        ApiResponseV1<ProviderDetails> apiResponse = ApiResponseV1.ok(providerDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<ApiResponseV1<ProviderDetails>>
    deleteProvider(
            @PathVariable long providerId,
            @NonNull HttpServletRequest request){
        ProviderDetails deletedProviderDetails = providerOrchestrator.deleteProvider(providerId);

        ApiResponseV1<ProviderDetails> apiResponse = ApiResponseV1.ok(deletedProviderDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }
}
