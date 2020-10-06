export default{
    URL: 'http://localhost:8399/api',
    ROUTES:{
        login: '/user/login',

        conversation: '/conv/:selectedDate',

        //Word
        getWordbook: '/wordbook/', // 뒤에 유저 id
        getBaseWordbook : '/wordbook/base',
        addWordbook: '/wordbook',
        addWord: '/wordbook/word'
    }
}
