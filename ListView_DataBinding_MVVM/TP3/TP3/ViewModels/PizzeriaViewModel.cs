using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;

namespace TP3.ViewModels
{
    public class PizzeriaViewModel : SimpleObservableObject
    {
        public ObservableCollection<PizzaViewModel> Pizzas { get; set; }

        public PizzeriaViewModel()
        {
            // Récupération des pizzas depuis le service métier
            List<PizzaViewModel> pizzasVm = Services.PizzeriaService.Instance.GetPizzas()
                                                    .Select(p => new PizzaViewModel(p))
                                                    .ToList();

            // Instanciation de la collection observée avec la liste des pizzas view models
            this.Pizzas = new ObservableCollection<PizzaViewModel>(pizzasVm);
        }
    }
}
