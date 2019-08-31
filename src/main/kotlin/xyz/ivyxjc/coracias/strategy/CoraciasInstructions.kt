package xyz.ivyxjc.coracias.strategy

class CoraciasInstructions {
    var createHeader = true
    var formatter: CoraciasFormatter = DefaultCoraciasFormatter()
    var coraciasDataTypeStrategy: CoraciasDataTypeStrategy = DefaultCoraciasDataTypeStrategy()
}