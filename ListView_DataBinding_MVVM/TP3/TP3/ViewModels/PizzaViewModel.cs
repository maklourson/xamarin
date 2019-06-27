using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace TP3.ViewModels
{
    public class PizzaViewModel : SimpleObservableObject
    {
        private Models.Pizza _pizza;

        private string _Nom;
        public string Nom
        {
            get { return _Nom; }
            set { this.Set(ref this._Nom, value); }
        }

        private bool _EstVegetarien;
        public bool EstVegetarien
        {
            get { return _EstVegetarien; }
            set { this.Set(ref this._EstVegetarien, value); }
        }

        private double _Prix;
        public double Prix
        {
            get { return _Prix; }
            set { this.Set(ref this._Prix, value); }
        }

        private string _Ingredients;
        public string Ingredients
        {
            get { return _Ingredients; }
            set { this.Set(ref this._Ingredients, value); }
        }

        public PizzaViewModel()
        {

        }

        public PizzaViewModel(Models.Pizza pizza)
        {
            this._pizza = pizza;

            this.Nom = pizza.Nom;
            this.EstVegetarien = pizza.EstVegetarien;
            this.Ingredients = pizza.Ingredients;
            this.Prix = pizza.Prix;
        }
    }
}
