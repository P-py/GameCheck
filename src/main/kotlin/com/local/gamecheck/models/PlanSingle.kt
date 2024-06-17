package com.local.gamecheck.models

 class PlanSingle(
    tipo: String, id: Int = 0): Plan(tipo, id) {

     override fun obterValor(rent: RentRelation): Double {
         var valorOriginal = super.obterValor(rent)
         if (rent.user.media > 8) {
             valorOriginal -= valorOriginal * 0.1
         }
         return valorOriginal
     }

     override fun toString(): String {
         return "Plano Avulso\n" +
                 "Tipo: $tipo\n" +
                 "Id: $id\n" + "\n"
     }
 }
