<template>
  <v-row justify="center">
    <v-btn
      class="mx-2"
      fab
      dark
      small
      color="var(--main-point-color)"
      @click.stop="dialog = true"
    >
      <v-icon dark>mdi-plus</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-toolbar color="var(--main-sub-color)">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title><h4>단어장 추가</h4></v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-card-text>
          <div>
            <v-text-field
              label="단어장 이름"
              :rules="rules"
              hide-details="auto"
              v-model="addWordBookInfo.name"
              class="mt-6"
            ></v-text-field>
          </div>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="addWordBook"> 저장 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import SERVER from "../../api/spring.js";
import { mapGetters } from "vuex";

export default {
  name: "WordbookCreate",
  data() {
    return {
      dialog: false,
      rules: [
        (value) => !!value || "Required.",
        (value) =>
          (value && value.length >= 2) || "두 글자 이상으로 입력해주세요.",
      ],
      addWordBookInfo: {
        name: "",
        user_id: "",
      },
    };
  },
  methods: {
    reset() {
      this.addWordBookInfo.name = "";
      this.addWordBookInfo.user_id = "";
    },
    addWordBook() {
      axios
        .post(SERVER.URL + SERVER.ROUTES.addWordbook, this.addWordBookInfo)
        .then((res) => {
          this.$store.commit("SET_WORDBOOK", res);
          this.$emit("close");
          this.dialog = false;
        })
        .catch((err) => console.log(err.response));
    },
  },
  computed: {
    ...mapGetters("Kakao", ["account"]),
  },
  created() {
    this.addWordBookInfo.user_id = JSON.parse(
      localStorage.getItem("vuex")
    ).Kakao.account.userId;
  },
};
</script>