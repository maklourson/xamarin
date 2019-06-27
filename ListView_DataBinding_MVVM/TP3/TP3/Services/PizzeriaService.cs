using System;
using System.Collections.Generic;
using System.Text;
using TP3.Models;

namespace TP3.Services
{
    public class PizzeriaService
    {
        private List<Pizza> _Pizzas;

        private PizzeriaService() { this.MoqPizzas(); }
        private static PizzeriaService _Instance;
        public static PizzeriaService Instance
        {
            get
            {
                if (_Instance == null)
                    _Instance = new PizzeriaService();

                return _Instance;
            }
        }      

        public List<Pizza> GetPizzas()
        {
            return this._Pizzas;
        }

        private void MoqPizzas()
        {
            this._Pizzas = new List<Pizza>
            {
                new Pizza{ Nom = "Marguarita", EstVegetarien = true, Ingredients ="Fromage, tomates", Prix = 9.8},
                new Pizza{ Nom = "Royale", Ingredients ="Jambon, Champignons, Fromage, tomates", Prix = 11.5},
                new Pizza{ Nom = "4 fromages", EstVegetarien = true, Ingredients ="Chèvre, Gorgonzola, Emmental, Mozarella", Prix = 12},
            };
        }
    }
}
