<template>
  <v-row justify="center">
    <v-btn class="mx-2" fab dark color="indigo" @click.stop="dialog = true">
      <v-icon dark>mdi-plus</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">단어장 추가</v-card-title>
        <v-card-text>
          <div>
            <v-text-field
              label="단어장 이름"
              :rules="rules"
              hide-details="auto"
              v-model="addWordBookInfo.name"
            ></v-text-field>
          </div>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="dialog = false">
            취소
          </v-btn>

          <v-btn color="green darken-1" text @click="addWordBook">
            저장
          </v-btn>
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
      this.addWordBookInfo.name = ""
      this.addWordBookInfo.user_id = ""
    },
    addWordBook() {
      axios
        .post(SERVER.URL + SERVER.ROUTES.addWordbook, this.addWordBookInfo)
        .then((res) => {
          this.$store.commit('SET_WORDBOOK', res)
          this.reset();
          this.$emit("close")
          this.dialog = false
        })
        .catch((err) => console.log(err.response));
    },
  },
  computed: {
    ...mapGetters('Kakao', ['account'])
  },
  mounted() {
    this.addWordBookInfo.user_id = this.account.userId
  }
};
</script>