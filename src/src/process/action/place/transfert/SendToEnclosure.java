package process.action.place.transfert;

import data.structure.Enclos;
import data.structure.Etable;
import data.structure.Poulallier;

public class SendToEnclosure  implements TransfertVisitor<Enclos>{

    @Override
    public void transfert(Etable etable, Enclos actionnable) {
        System.out.println("On tranfert les animaux de l'étable vers l'enclos");
    }

    @Override
    public void transfert(Poulallier poulallier, Enclos actionnable) {
        System.out.println("On tranfert les poulailler de l'étable vers l'enclos");
    }
    
}
