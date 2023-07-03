package com.smartbusinessbackend.smartbusinesscard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private String productName;
    private String material;
    private String price;
    private boolean supports_Printing;
    private boolean supports_Engraving;
}
