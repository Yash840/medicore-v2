package org.cross.medicore.scheduling.internals.mapper;

import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;
import org.cross.medicore.scheduling.internals.entity.Provider;
import org.cross.medicore.scheduling.internals.entity.ProviderAddress;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public static String normalizeText(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+", " ");
    }

    public static Provider toProvider(CreateProviderDto createProviderDto) {
        if (createProviderDto == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setFirstName(normalizeText(createProviderDto.firstName()));
        provider.setLastName(normalizeText(createProviderDto.lastName()));
        provider.setMiddleName(normalizeText(createProviderDto.middleName()));
        provider.setEmail(createProviderDto.email() != null ? createProviderDto.email().toLowerCase() : null);
        provider.setSpecialization(createProviderDto.specialization());
        provider.setMmcRegNumber(createProviderDto.mmcRegNumber());
        return provider;
    }

    public static ProviderBriefProfile toProviderBriefProfile(Provider provider) {
        if (provider == null) {
            return null;
        }
        return new ProviderBriefProfile(
                provider.getProviderId(),
                provider.getUserId(),
                provider.getFirstName(),
                provider.getMiddleName(),
                provider.getLastName(),
                provider.getEmail(),
                provider.getPhoneNumber(),
                provider.getSpecialization(),
                provider.getMmcRegNumber()
        );
    }

    public static ProviderDetails toProviderDetails(Provider provider) {
        if (provider == null) {
            return null;
        }
        return new ProviderDetails(
                provider.getProviderId(),
                provider.getFirstName(),
                provider.getMiddleName(),
                provider.getLastName(),
                provider.getEmail(),
                provider.getPhoneNumber(),
                provider.getBirthDate(),
                provider.getBloodGroup(),
                provider.getSex(),
                toProviderAddressDetails(provider.getAddress()),
                provider.getSpecialization(),
                provider.getMmcRegNumber()
        );
    }

    public static void updateProviderFromDto(UpdateProviderDetailsDto dto, Provider provider) {
        if (dto == null || provider == null) {
            return;
        }

        if (dto.firstName() != null) {
            provider.setFirstName(normalizeText(dto.firstName()));
        }
        if (dto.lastName() != null) {
            provider.setLastName(normalizeText(dto.lastName()));
        }
        if (dto.middleName() != null) {
            provider.setMiddleName(normalizeText(dto.middleName()));
        }
        if (dto.email() != null) {
            provider.setEmail(dto.email().toLowerCase());
        }
        if (dto.phoneNumber() != null) {
            provider.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.birthDate() != null) {
            provider.setBirthDate(dto.birthDate());
        }
        if (dto.bloodGroup() != null) {
            provider.setBloodGroup(dto.bloodGroup());
        }
        if (dto.sex() != null) {
            provider.setSex(dto.sex());
        }
        if (dto.specialization() != null) {
            provider.setSpecialization(dto.specialization());
        }
        if (dto.mmcRegNumber() != null) {
            provider.setMmcRegNumber(dto.mmcRegNumber());
        }

        if (dto.address() != null) {
            if (provider.getAddress() == null) {
                provider.setAddress(new ProviderAddress());
            }
            updateProviderAddressFromDto(dto.address(), provider.getAddress());
        }
    }

    private static ProviderDetails.ProviderAddressDetails toProviderAddressDetails(ProviderAddress address) {
        if (address == null) {
            return null;
        }
        return new ProviderDetails.ProviderAddressDetails(
                address.getStreet(),
                address.getBuilding(),
                address.getLandmark(),
                address.getFlat(),
                address.getTown(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipCode()
        );
    }

    private static void updateProviderAddressFromDto(ProviderDetails.ProviderAddressDetails dto, ProviderAddress address) {
        if (dto == null || address == null) {
            return;
        }
        if (dto.street() != null) {
            address.setStreet(dto.street());
        }
        if (dto.building() != null) {
            address.setBuilding(dto.building());
        }
        if (dto.landmark() != null) {
            address.setLandmark(dto.landmark());
        }
        if (dto.flat() != null) {
            address.setFlat(dto.flat());
        }
        if (dto.town() != null) {
            address.setTown(dto.town());
        }
        if (dto.city() != null) {
            address.setCity(dto.city());
        }
        if (dto.state() != null) {
            address.setState(dto.state());
        }
        if (dto.country() != null) {
            address.setCountry(dto.country());
        }
        if (dto.zipCode() != null) {
            address.setZipCode(dto.zipCode());
        }
    }
}
