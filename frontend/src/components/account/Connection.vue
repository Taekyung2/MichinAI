<template>
  <div>
    <div class="connect-container">
      <div>
        <h1 style="color: var(--main-sub-color)">
          미친AI
          <img src="@/assets/icon/cross.svg" alt="cross.svg" width="20vw" />
          Kakao
        </h1>

        <div class="connect-text">
          <div class="connect-text-sub1">
            <p style="margin-top: 20px; margin-bottom: 0px">
              영어 공부, 미친AI와 대화만 하면 끝?
              <span
                style="
                  font-weight: bold;
                  font-size: 1.15rem;
                  color: var(--font-base-color);
                "
                >NO!!</span
              >
            </p>
            <p style="margin-top: 8px; margin-bottom: 0px">
              Perfect한 공부를 위해
              <span
                style="
                  font-weight: bold;
                  font-size: 1.15rem;
                  color: var(--font-base-color);
                "
                >미친AI</span
              >는, <br />
              Spell과 Grammar 같은
              <span class="connect-point-text">철자, 문법, 문맥 검사</span>와
              <br />
              자신의 실력을 한 눈에 볼 수 있는
              <span class="connect-point-text">통계</span> 등의 서비스를 <br />
              Web Site를 통해 제공해드립니다.
            </p>
          </div>
          <div class="connect-text-sub2">
            <div class="connect-img">
              <img
                src="@/assets/michinLogo02.png"
                width="40px"
                alt="michin.png"
              />
              <img src="@/assets/icon/cross.svg" alt="cross.svg" width="15px" />
              <img
                src="@/assets/kakaoLogo02.png"
                width="40px"
                alt="kakao.png"
              />
            </div>
            <p
              style="
                margin-top: 10px;
                margin-bottom: 20px;
                font-weight: bold;
                font-size: 1.15rem;
                color: var(--font-base-color);
              "
            >
              미친AI의 1000% 활용을 위해
              <br />반드시
              <span style="color: var(--main-sub-color)">Kakao</span> 와
              연동해야 합니다.
            </p>

            <div class="connect-steps">
              <p class="connect-step-item">
                <span class="connect-step">STEP 1. </span>
                <span class="connect-btn-model">CONNECT WITH KAKAO</span>
                버튼 클릭
              </p>
              <p>
                <span class="connect-step">STEP 2. </span> 카카오 계정으로
                로그인
              </p>
              <p><span class="connect-step">STEP 3. </span> 연동 완료! :></p>
            </div>
          </div>
        </div>
      </div>
      <KakaoLogin />
    </div>
    <div class="intro-container" v-if="false">
      <Introduction />
    </div>
  </div>
</template>

<script>
import KakaoLogin from "@/components/account/KakaoLogin.vue";
import Introduction from "@/components/Introduction.vue";

export default {
  name: "Connection",
  components: {
    KakaoLogin,
    Introduction,
  },
};
</script>

<style>
.connect-container {
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 50px 30px;
  justify-content: space-between;

  background: var(--main-color);
  background-image: url("../../assets/connection_main.png");
  background-repeat: no-repeat, repeat;
  background-position-y: bottom;
  background-position-x: right;
}
.connect-container > h1 {
  font-size: 2.5rem;
}
.connect-text {
  font-size: 1rem !important;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 65vh;
}
.connect-step {
  font-weight: bold;
  padding-right: 5px;
  color: var(--font-base-color);
}

.connect-img {
  display: flex;
  align-items: center;
  margin-left: -5px;
}

.connect-point-text {
  font-weight: bold;
  color: var(--font-base-color);
}

.connect-btn-model {
  color: #faed38;
  background: #3e2622;
  font-size: 10px;
  padding: 5px 8px;
  border-radius: 6px;
  margin-right: 3px;
  font-family: "BMJUA" !important;
}

.connect-step-item {
  display: flex;
  align-items: center;
}

@media (min-width: 0px) and (max-width: 428px) {
  .connect-text-sub1 p {
    font-size: 0.9rem;
    color: var(--font-sub-3-color);
  }
  .connect-text-sub2 p {
    font-size: 0.9rem;
    color: var(--font-sub-3-color);
  }

  .connect-steps p {
    margin: 10px 0px;
  }
}

@media (min-width: 0px) and (max-width: 600px) {
  .connect-container {
    padding: 50px 30px 50px 30px;
    background-size: 50vh;
  }
  .connect-text {
    max-width: 350px;
    height: 98%;
  }
}
@media (min-width: 601px) and (max-width: 1024px) {
  .connect-container {
    padding: 50px 30px;
    background-size: 50vh;
  }
}
@media (min-width: 1025px) and (max-width: 1904px) {
  .connect-container {
    padding: 60px 100px;
    background-size: 50%;
  }
}
</style>