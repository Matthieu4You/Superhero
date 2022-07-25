package com.mattu.superherosighting.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Superhero {
    int Sid;
    String Sname;
    String Sdescription;
    String Ssp;
    int Svillian;
}
