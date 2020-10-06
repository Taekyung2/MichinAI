<template>
  <div class="outgoing_msg" style="margin: 15px">
    <div class="sent_msg">
      <p class="text-capitalize">
        {{ chat.eng }}<br />
        <span>{{ chat.kor }}</span>
      </p>
      <img
        class="incoming_msg_img"
        :src="require(`@/assets/convImg/${chat.speaker}.png`)"
      /><!-- <span class="time_date">{{this.time}}</span> -->
    </div>
  </div>
</template>
 
<script>
export default {
  name: "sendMsg",
  components: {},
  props: {
    chat: Object,
  },
  data() {
    return {
      splitMsg: [],
      time: "",
    };
  },
};
</script>
<style>
.sent_msg p {
  background: #f2f2f2 none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 90%;
  display: inline-block;
}
.sent_msg * {
  color: #646464;
}
.outgoing_msg {
  overflow: hidden;
}
.sent_msg {
  float: right;
  width: 85%;
}
.outgoing_msg_img {
  display: inline-block;
  width: 10%;
}
</style>