package coelho.caique.clima.data.provider

import coelho.caique.clima.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}