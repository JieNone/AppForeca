package fedortyurin.appforeca.model

import java.io.Serializable

data class Forecast(
    var datetime: String,
    var team1: String,
    var team2: String,
    var flag: String,
    var liga: String,
    var prognoz: String,
    var vero1: String,
    var vero2: String,
    var vero3: String,
    var coef1: String,
    var coef2: String,
    var coef3: String,
    var result: String,
    var score1: String,
    var score2: String,
    var color: String
): Serializable