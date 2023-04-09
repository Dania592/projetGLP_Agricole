package process.action.place.transfert;

import data.structure.Abatoire;
import data.structure.Etable;
import data.structure.Poulallier;

public class SendToSlaughterHouser implements TransfertVisitor<Abatoire> {

    @Override
    public void transfert(Etable etable, Abatoire actionnable) {
        System.out.println("On tranfert les animaux de l'étable vers l'abatoire");;
    }

    @Override
    public void transfert(Poulallier poulallier, Abatoire actionnable) {
        System.out.println("On transfert les animaux du poulailler vers l'abatoire");
    }
    
}
