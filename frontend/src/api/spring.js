export default{
    URL: 'http://localhost:8399/api',
    ROUTES:{
        login: '/user/login',

        conversation: '/api/conv/:selectedDate',

        //Word
        getWordbook: '/wordbook/', // 뒤에 유저 id
        getBaseWordbook : '/wordbook/base',
        addWordbook: '/wordbook'

    }
}
