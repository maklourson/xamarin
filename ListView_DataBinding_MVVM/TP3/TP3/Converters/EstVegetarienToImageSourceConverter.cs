using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using Xamarin.Forms;

namespace TP3.Converters
{
    public class EstVegetarienToImageSourceConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            bool? estVegetarien = (bool?)value;

            string imageResource;

            if (estVegetarien != null && estVegetarien.Value)
            {
                imageResource = "vegetarien.jpg";
            }
            else
            {
                imageResource = "carnivore.jpg";
            }

            return ImageSource.FromResource("TP3.Resources." + imageResource);
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
