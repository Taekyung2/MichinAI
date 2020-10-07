<template>
  <!-- User 입장에서 생각하기 ~.~ -->
  <div class="incoming_msg">
    <img class="incoming_msg_img" src="./michin.png" alt="sunil" />
    <div class="received_msg">
      <div class="received_withd_msg">
        <p class="text-capitalize">{{ chat.msg }}</p>
        <span class="time_date"> {{ this.time }} </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "receiveMsg",
  props: {
    chat: {
      type: Object,
      required: true,
    },
  },

  data() {
    return {
      time: "",
    };
  },

  created() {
    var moment = require("moment");
    this.time = moment(this.chat.time.slice(0, 8), ["HH:mm a"]).format("LT");
  },
};
</script>

<style scoped>
.incoming_msg_img {
  display: inline-block;
  width: 11%;
}
.received_msg {
  display: inline-block;
  margin-top: 3px;
  padding: 0 0 0 5px;
  vertical-align: top;
  width: 89%;
}
.received_withd_msg p {
  background: #f2f2f2 none repeat scroll 0 0;
  border-radius: 3px;
  /* color: #f2f2f2;ebebeb */
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 1px;
  margin: 5px 5px 3px;
}
.received_withd_msg {
  width: 57%;
}
</style>