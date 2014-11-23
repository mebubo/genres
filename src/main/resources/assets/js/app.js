var Word = Backbone.Model.extend({
});

var AppView = Backbone.View.extend({
    el: "#genres-app",
    template: _.template($("#card-template").html()),
    initialize: function () {
        this.getNew();
    },
    events: {
        'click button#f': 'f',
        'click button#m': 'm'
    },
    f: function () {
        this.sendReply('f');
    },
    m: function () {
        this.sendReply('m');
    },
    addLog: function (g) {
        var status = this.getWord().genre === g ? "Correct:" : "Wrong. Should be:";
        var article = this.getWord().genre === 'm' ? 'un' : "une";
        $("#genres-log").prepend($("<li>" + status + " " + article + " " + this.getWord().lemme + "</li>"));
    },
    sendReply: function (g) {
        this.addLog(g);
        var that = this;
        $.ajax({
            url: "/api/word/" + this.getWord().id + "/" + g,
            type: "PUT"
        }).done(function (data) {
            that.getNew();
        })
    },
    getNew: function () {
        var that = this;
        $.ajax("/api/word").done(function (data) {
            that.learnedWord = new Word(data);
            that.render();
        });
    },
    getWord: function () {
        return this.learnedWord.get("word");
    },
    render: function () {
        this.$el.html(this.template(this.getWord()));
    }
});

new AppView;


