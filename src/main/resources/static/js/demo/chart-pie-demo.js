// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

const all =document.querySelector("#all").value;
const month =document.querySelector("#month").value;
const del =document.querySelector("#del").value;

// Pie Chart Example
var ctx = document.getElementById("myPieChart");

var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["전체 원생 수", "신규 원생 수", "퇴원생 수"],
    datasets: [{
      data: [all, (month-del), del],
      backgroundColor: ['#FB7488', '#AA4DB9', '#F2F1F5'],
      hoverBackgroundColor: [ '#F05165', '#4E3064','#5f73b3'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
