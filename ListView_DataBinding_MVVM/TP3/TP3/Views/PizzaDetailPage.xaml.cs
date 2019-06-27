using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP3.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TP3.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PizzaDetailPage : ContentPage
	{
		public PizzaDetailPage (PizzaViewModel pizzaViewModel)
		{
			InitializeComponent ();
            this.BindingContext = pizzaViewModel;
		}
	}
}