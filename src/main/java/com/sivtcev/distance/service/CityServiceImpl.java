package com.sivtcev.distance.service;

import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.exception.BadRequestException;
import com.sivtcev.distance.exception.ResourceNotFoundException;
import com.sivtcev.distance.model.AncillaryEntity;
import com.sivtcev.distance.model.City;
import com.sivtcev.distance.model.Distance;
import com.sivtcev.distance.repository.CityRepository;
import com.sivtcev.distance.repository.DistanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;

    @Override
    public DataResponse<CityResponse> getCitiesList() {
        List<CityResponse> cityResponseList = cityRepository.findAll()
                .stream()
                .map(CityResponse::new)
                .collect(Collectors.toList());
        if (cityResponseList.isEmpty()) {
            throw new ResourceNotFoundException("no cities found");
        }
        return new DataResponse<>(cityResponseList);
    }

    @Override
    public void uploadCitiesFromXml(MultipartFile multipartFile) {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(multipartFile.getInputStream());

            JAXBContext jaxbContext = JAXBContext.newInstance(AncillaryEntity.class, City.class, Distance.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            AncillaryEntity ancillaryEntity = (AncillaryEntity) unmarshaller.unmarshal(inputStream);

            cityRepository.saveAll(ancillaryEntity.getCityList());
            distanceRepository.saveAll(ancillaryEntity.getDistanceList());

        } catch (Exception e) {
            throw new BadRequestException("problem with file upload " + e.getLocalizedMessage());
        }
    }
}
