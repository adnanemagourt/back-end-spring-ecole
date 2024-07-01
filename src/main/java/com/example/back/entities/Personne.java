package com.example.back.entities;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Personne {
    public String email;

    private String motDePasse;

    public String getEmail() { return email; }

    public String getMotDePasse() { return motDePasse; }

}
