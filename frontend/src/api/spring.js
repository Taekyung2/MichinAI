export default{
    URL: 'http://localhost:8399/api',
    ROUTES:{
        login: '/account/login/kakao',
        conversation: '/api/conv/:selectedDate',

        //Note
        getWordbook: '/wordbook/', // 뒤에 유저 id
        getBaseWordbook : '/wordbook/base'
    }
}