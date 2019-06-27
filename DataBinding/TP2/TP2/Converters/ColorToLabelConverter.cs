using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using Xamarin.Forms;

namespace TP2.Converters
{
    public class ColorToLabelConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            Color? color = (Color?)value;

            if (color == null)
                return "Aucune couleur reçue.";

            return string.Format(
                "R={0:F0}, G={1:F0}, B={2:F0}",
                color.Value.R * 255,
                color.Value.G * 255,
                color.Value.B * 255);
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
