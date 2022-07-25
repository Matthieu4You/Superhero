package com.mattu.superherosighting.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Superheromember {
    int SMid;
    Superhero hero;
    Orgnization org;
}
