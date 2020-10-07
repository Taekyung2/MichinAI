<template>
  <div class="outgoing_msg" style="margin: 15px">
    <div class="sent_msg">
      <p class="text-capitalize">
        <span v-if="lang.includes(0)">{{ chat.eng }}<br /></span>
        <span v-if="lang.includes(1)">{{ chat.kor }}</span>
      </p>
      <img
        class="outgoing_msg_img"
        :src="require(`@/assets/convImg/${chat.speaker}.png`)"
      /><!-- <span class="time_date">{{ this.time }}</span> -->
    </div>
  </div>
</template>
 
<script>
export default {
  name: "sendMsg",
  components: {},
  props: {
    chat: Object,
    lang: {
      type: Array,
    },
  },
  data() {
    return {
      splitMsg: [],
      time: "",
    };
  },
};
</script>
<style scoped>
.sent_msg p {
  background: #dcdcdc none repeat scroll 0 0;
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
.time_date {
  color: #747474;
  display: block;
  font-size: 1px;
  margin: 5px 5px 3px;
}
.sent_msg {
  float: right;
  width: 85%;
}
.outgoing_msg_img {
  display: inline-block;
  width: 9%;
  margin-left: 2px;
  vertical-align: top;
}
</style>