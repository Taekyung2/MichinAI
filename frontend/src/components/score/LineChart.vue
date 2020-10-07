<script>
import { Line } from "vue-chartjs";

export default {
  name: "LineChart",
  extends: Line,
  props: ["chartData"],
  data: () => ({
    datacollection: {},
  }),
  watch: {
    chartData() {
      this.init();
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      let color = "#7DB3D9";
      // let pointColor = "#F48705";
      this.datacollection = {
        labels: this.chartData.labels,
        datasets: [
          {
            label: "chat score",
            borderWidth: 3,
            pointRadius: 5,
            borderColor: color,
            backgroundColor: color,
            // pointBorderColor: pointColor,
            // pointBackgroundColor: pointColor,
            fill: false,
            data: this.chartData.data,
          },
        ],
      };

      this.render();
    },
    render() {
      this.renderChart(this.datacollection, {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: false,
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          display: false,
        },
        responsive: true,
        maintainAspectRatio: false,
        onClick: this.handle,
      });
    },
    handle(pointer, event) {
      if (event.length == 0) return;

      this.$emit("on-receive", {
        idx: event[0]._index,
      });
    },
  },
};
</script>

<style>
.v-sheet--offset {
  top: -24px;
  position: relative;
}
</style>