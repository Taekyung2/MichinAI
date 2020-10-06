<script>
import { Doughnut } from "vue-chartjs";

export default {
  name: "PieChart",
  extends: Doughnut,
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
      let colors = ["#7DB3D9", " #F2CC06", " #F48705", "#a6a6a6"];
      this.datacollection = {
        labels: this.chartData.labels,
        datasets: [
          {
            backgroundColor: colors,
            data: this.chartData.data,
          },
        ],
      };

      this.render();
    },
    render() {
      this.renderChart(this.datacollection, {
        legend: {
          display: false,
        },
        responsive: true,
        maintainAspectRatio: false,
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