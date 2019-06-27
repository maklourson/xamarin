using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

namespace TP3.UWP
{
    public sealed partial class MainPage
    {
        public MainPage()
        {
            this.InitializeComponent();

            // Force la culture FR pour l'UI
            Thread.CurrentThread.CurrentUICulture = new System.Globalization.CultureInfo("FR-fr");
            Thread.CurrentThread.CurrentCulture = Thread.CurrentThread.CurrentUICulture;

            LoadApplication(new TP3.App());
        }
    }
}
