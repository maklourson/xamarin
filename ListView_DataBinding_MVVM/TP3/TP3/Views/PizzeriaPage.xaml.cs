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
	public partial class PizzeriaPage : ContentPage
	{
		public PizzeriaPage ()
		{
			InitializeComponent ();
		}

        private void ListView_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            this.Navigation.PushAsync(new PizzaDetailPage(e.Item as PizzaViewModel));
        }
    }
}