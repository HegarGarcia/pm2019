using System.Collections.Generic;
using System.ComponentModel;
using Xamarin.Forms;
using Microcharts;
using SkiaSharp;

namespace graphics
{
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        private readonly List<Microcharts.Entry> _entries = new List<Microcharts.Entry>()
        {
            new Microcharts.Entry(200) { Label = "January", ValueLabel = "200", Color = SKColor.Parse("#FF0033") },
            new Microcharts.Entry(400)
            {
                Label = "February",
                ValueLabel = "400",
                Color = SKColor.Parse("#FF8000")
            },
            new Microcharts.Entry(300)
            {
                Label = "March",
                ValueLabel = "300",
                Color = SKColor.Parse("#FFE600")
            },
            new Microcharts.Entry(250)
            {
                Label = "April",
                ValueLabel = "250",
                Color = SKColor.Parse("#1AB34D")
            },
            new Microcharts.Entry(650)
            {
                Label = "May",
                ValueLabel = "650",
                Color = SKColor.Parse("#1A66FF")
            },
            new Microcharts.Entry(500)
            {
                Label = "June",
                ValueLabel = "500",
                Color = SKColor.Parse("#801AB3")
            },
        };
        public MainPage()
        {
            InitializeComponent();
            MyDonutChart.Chart = new DonutChart { Entries = _entries };
            MyBarChart.Chart = new BarChart { Entries = _entries };
            MyPointChart.Chart = new PointChart { Entries = _entries };
            MyRadialGaugeChart.Chart = new RadialGaugeChart { Entries = _entries };
            MyLineChart.Chart = new LineChart { Entries = _entries };
        }
    }
}
