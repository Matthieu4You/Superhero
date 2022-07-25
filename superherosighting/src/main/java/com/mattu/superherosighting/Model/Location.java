package com.mattu.superherosighting.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    int Lid;
    String Lname;
    String Ldescription;
    String Laddress;
    BigDecimal Llatitude;
    BigDecimal Llongtitude;
}
