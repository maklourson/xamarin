using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace TP2
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();

            Random r = new Random();

            this.sliderR.Value = r.NextDouble();
            this.sliderG.Value = r.NextDouble();
            this.sliderB.Value = r.NextDouble();
        }

        private void Slider_ValueChanged(object sender, ValueChangedEventArgs e)
        {
            this.boxView.Color = Color.FromRgb(
                this.sliderR.Value,
                this.sliderG.Value,
                this.sliderB.Value);
        }
    }
}
