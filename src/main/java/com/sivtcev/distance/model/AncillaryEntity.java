package com.sivtcev.distance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class AncillaryEntity {

    @XmlElement(name = "city", nillable = true)
    private List<City> cityList = null;

    @XmlElement(name = "distance", nillable = true)
    private List<Distance> distanceList = null;
}
