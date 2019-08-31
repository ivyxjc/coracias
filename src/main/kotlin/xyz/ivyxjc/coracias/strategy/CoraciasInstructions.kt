package xyz.ivyxjc.coracias.strategy


/**
 * CoraciasInstruction is the container of the formatter and typeStrategy, [CoraciasFormatter] uses
 * it as the parameter so CoraciasFormatter can access the formatter and typeStrategy.
 * @see CoraciasFormatter
 * @see CoraciasDataTypeStrategy
 */
class CoraciasInstructions {
    var createHeader = true
    var formatter: CoraciasFormatter = DefaultCoraciasFormatter()
    var coraciasDataTypeStrategy: CoraciasDataTypeStrategy = DefaultCoraciasDataTypeStrategy()
}