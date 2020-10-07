<template>
<div class="outgoing_msg">
    <div class="sent_msg">
        <p>
            <template v-for="(msg, i) in this.splitMsg">
                <span :key="i" v-if="msg[1] == ''">{{msg[0]}}</span>
                <correction :msg="msg" :key="i" v-else></correction>
            </template>
        </p>
        <span class="time_date">{{this.time}}</span>
    </div>
</div>
</template>
 
<script>
import correction from "./correction.vue";

export default {
    name: 'sendMsg',

    components: {
        correction,
    },

    props: {
        chat: Object,
    },

    data(){
        return {
            splitMsg: [],
            time: '',
        }
    },

    created() {
        var moment = require('moment');
        this.time = moment(this.chat.time.slice(0,8), ['HH:mm a']).format('LT');
    },

    mounted() {
        // console.log(this.chat)
        var idx = 0;
        var that = this;
        var msg = this.chat.msg;

        if (this.chat.check.length != 0) {
            this.chat.check.forEach(function(eachCheck){
                var general = that.chat.msg.substring(idx, eachCheck.fromPos);
                that.splitMsg.push([general, ""]);
                var particular = that.chat.msg.substring(eachCheck.fromPos, eachCheck.toPos)
                var recs = "";
                if (eachCheck.category !== "") {
                    recs += "["+eachCheck.category + "]<br>"
                }
                
                eachCheck.recommends.forEach(function(eachRec){
                    recs += eachRec + "<br>";
                });
                that.splitMsg.push([particular, recs]);

                idx = eachCheck.toPos;
            });
            
            this.splitMsg.push([msg.substring(idx), ""])
        } else {
            this.splitMsg.push([msg, ""]);
        }
    }
}
</script>

<style>
.sent_msg p {
  background: #7DB3D9 none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; }
.sent_msg {
  float: right;
  width: 46%;
}
</style>