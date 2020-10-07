<template>
  <!-- User 입장에서 생각하기 ~.~ -->
  <div class="incoming_msg" style="margin: 15px">
    <img
      class="incoming_msg_img"
      :src="require(`@/assets/convImg/${chat.speaker}.png`)"
    />
    <div class="received_msg">
      <div class="received_withd_msg">
        <p class="text-capitalize">
          <span v-if="lang.includes(0)">{{ chat.eng }}<br /></span>
          <span v-if="lang.includes(1)">{{ chat.kor }}</span>
        </p>
        <!-- <span class="time_date"> {{this.time}} </span> -->
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
    },
    lang:{
      type:Array
    },
  },
  data() {
    return {
      time: "",
    };
  },
  methods: {},
};
</script>
<style scoped>
.incoming_msg_img {
  display: inline-block;
  width: 8%;
}
.received_msg {
  display: inline-block;
  margin-top: 3px;
  padding: 0 0 0 5px;
  vertical-align: top;
  width: 89%;
}
.received_withd_msg p {
  background: #fbb124 none repeat scroll 0 0;
  border-radius: 3px;
  /* color: #f2f2f2;ebebeb */
  /* color: #fff; */
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.received_withd_msg * {
  color: #fff;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 1px;
  margin: 5px 5px 3px;
}
.received_withd_msg {
  width: 80%;
}
</style>