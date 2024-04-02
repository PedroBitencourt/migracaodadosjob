package com.estudos.migracaodados.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankData {
    private int id;

    private int personId;

    private int agency;

    private int account;

    private int bank;
}
