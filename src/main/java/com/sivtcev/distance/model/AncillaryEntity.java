package com.sivtcev.distance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class AncillaryEntity {

    @XmlElementWrapper(name= "cities", nillable = true, required = true)
    private List<City> cityList;

    @XmlElementWrapper(name= "distances", nillable = true, required = true)
    private List<Distance> distanceList;
}
