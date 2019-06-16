
theme: /

    state: newNode_0
        random:
            a: Привет!
            a: Приветствую Вас!
        image: https://248305.selcdn.ru/zfl_prod/23370876/23370879/6ilMEszoj4FS3ZWU.png
        go!: /newNode_3
    @Transition
        {
          "boundsTo" : "/newNode_0",
          "then" : "/newNode_294"
        }
    state: newNode_3
        go!: /newNode_294

    state: newNode_78
        random:
            a: Назовите Ваше кодовое слово.
        go!: /newNode_79
    @Transition
        {
          "boundsTo" : "/newNode_78",
          "then" : "/newNode_344"
        }
    state: newNode_79
        go!: /newNode_344
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "есть в базе?",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/data/${userId}",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_262",
          "errorState" : "/newNode_295",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "history",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_294
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/data/${userId}", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["history"] = $httpResponse;
                $reactions.transition("/newNode_262");
            } else {
                $reactions.transition("/newNode_295");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_294",
                name: "newNode_294 buttons",
                handler: function($context) {
                }
            });

    state: newNode_262
        random:
            a: {{$session.history.UName}}! Приятно Вас видеть. || tts = "", ttsEnabled = false
            a: {{$session.history.UName}}! Рад Вас видеть. || tts = "", ttsEnabled = false
        go!: /newNode_317
    @Transition
        {
          "boundsTo" : "/newNode_262",
          "then" : "/newNode_319"
        }
    state: newNode_317
        go!: /newNode_319
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "кодовое слово",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_345",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "йй"
                }
              ],
              "then" : "/newNode_345"
            }
          ]
        }
    state: newNode_344
        state: 1
            e: йй

            go!: /newNode_345

        state: Fallback
            q: *
            go!: /newNode_345
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_344",
                name: "newNode_344 buttons",
                handler: function($context) {
                }
            });

    state: newNode_345
        if: $session.history.UPassword == $session.queryText
            go!: /newNode_348
        else:
            go!: /newNode_346

    state: newNode_410
        random:
            a: Теперь скажите Ваше кодовое слово, которое будет паролем.
        go!: /newNode_412
    @Transition
        {
          "boundsTo" : "/newNode_410",
          "then" : "/newNode_411"
        }
    state: newNode_412
        go!: /newNode_411

    state: newNode_272
        random:
            a: Давайте я запишу по новому.
            a: Назовите? как мне к Вам обращаться теперь:
        go!: /newNode_274
    @Transition
        {
          "boundsTo" : "/newNode_272",
          "then" : "/newNode_298"
        }
    state: newNode_274
        go!: /newNode_298
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [ ],
          "global" : true,
          "fallback" : "/newNode_356",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "записаться на техобслуживание"
                },
                {
                  "type" : "example",
                  "value" : "записать меня на техобслуживание"
                }
              ],
              "then" : "/newNode_407"
            }
          ]
        }
    state: newNode_343
        state: 1
            e!: записаться на техобслуживание
            e!: записать меня на техобслуживание

            go!: /newNode_407

        state: Fallback
            q: *
            go!: /newNode_356

    state: newNode_356
        random:
            a: Не буду отвечать!
            a: не стану отвечать.
            a: Лень отвечать на этот вопрос!
        go!: /newNode_357
    @Transition
        {
          "boundsTo" : "/newNode_356",
          "then" : "/newNode_343"
        }
    state: newNode_357
        go!: /newNode_343
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "проверяем в базе",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/data/${userId}",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_394",
          "errorState" : "/newNode_296",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "history",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_407
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/data/${userId}", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["history"] = $httpResponse;
                $reactions.transition("/newNode_394");
            } else {
                $reactions.transition("/newNode_296");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_407",
                name: "newNode_407 buttons",
                handler: function($context) {
                }
            });

    state: newNode_394
        random:
            a:  это демонстрационный пример {{$session.history.UName}}!
                Допустим что
        go!: /newNode_395
    @Transition
        {
          "boundsTo" : "/newNode_394",
          "then" : "/newNode_372"
        }
    state: newNode_395
        go!: /newNode_372

    state: newNode_372
        random:
            a: принимаем авто в 10-ть, 13-ть и 15 часов. На какую дату и время вы бы хотели записаться?
        go!: /newNode_374
    @Transition
        {
          "boundsTo" : "/newNode_372",
          "then" : "/newNode_373"
        }
    state: newNode_374
        go!: /newNode_373

    state: newNode_381
        random:
            a: Дата: {{$session.day}} {{$session.month}} Время: {{$session.time}}
        go!: /newNode_382
    @Transition
        {
          "boundsTo" : "/newNode_381",
          "then" : "/newNode_380"
        }
    state: newNode_382
        go!: /newNode_380

    state: newNode_380
        if: _.property($session.date)($session.result[$session.index]) === false
            go!: /newNode_383
        else:
            go!: /newNode_385

    state: newNode_392
        if: $session.res = _.findWhere($session.result, {"number":$session.NUMBER})
            go!: /newNode_393
        else:
            go!: /newNode_387

    state: newNode_258
        random:
            a: Извините, а можно вопрос?
        go!: /newNode_260
    @Transition
        {
          "boundsTo" : "/newNode_258",
          "then" : "/newNode_259"
        }
    state: newNode_260
        go!: /newNode_259
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "база",
          "actions" : [ ],
          "global" : true,
          "fallback" : "/newNode_8",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "pattern",
                  "value" : "пока"
                },
                {
                  "type" : "example",
                  "value" : "до свидания"
                },
                {
                  "type" : "example",
                  "value" : "ты мне надоел"
                },
                {
                  "type" : "example",
                  "value" : "мне помощь не нужна"
                },
                {
                  "type" : "example",
                  "value" : "Я ничего не хочу"
                },
                {
                  "type" : "example",
                  "value" : "спи давай"
                },
                {
                  "type" : "example",
                  "value" : "иди спать"
                },
                {
                  "type" : "example",
                  "value" : "я пошла спать"
                },
                {
                  "type" : "example",
                  "value" : "Пауза"
                },
                {
                  "type" : "example",
                  "value" : "отстань"
                },
                {
                  "type" : "example",
                  "value" : "ничего не надо"
                },
                {
                  "type" : "example",
                  "value" : "ничего не нужно"
                },
                {
                  "type" : "example",
                  "value" : "закончим разговор"
                },
                {
                  "type" : "example",
                  "value" : "завершить разговор"
                },
                {
                  "type" : "example",
                  "value" : "Выключайся"
                },
                {
                  "type" : "example",
                  "value" : "Отключись"
                },
                {
                  "type" : "example",
                  "value" : "хватит помогать"
                },
                {
                  "type" : "example",
                  "value" : "нечаянно нажала не туда"
                },
                {
                  "type" : "example",
                  "value" : "до завтра"
                },
                {
                  "type" : "example",
                  "value" : "Прощайте"
                },
                {
                  "type" : "example",
                  "value" : "спокойной ночи"
                },
                {
                  "type" : "example",
                  "value" : "верни обратно"
                },
                {
                  "type" : "example",
                  "value" : "верни как было"
                },
                {
                  "type" : "example",
                  "value" : "не туда попал"
                },
                {
                  "type" : "example",
                  "value" : "Закрой это всё"
                },
                {
                  "type" : "example",
                  "value" : "отменить"
                },
                {
                  "type" : "example",
                  "value" : "отмените"
                },
                {
                  "type" : "example",
                  "value" : "отбой"
                }
              ],
              "then" : "/newNode_242"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "привет"
                },
                {
                  "type" : "example",
                  "value" : "здравствуй"
                }
              ],
              "then" : "/newNode_0"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "расписание гимназии"
                },
                {
                  "type" : "example",
                  "value" : "расписание крымской гимназии"
                },
                {
                  "type" : "example",
                  "value" : "анонс мероприятий"
                },
                {
                  "type" : "example",
                  "value" : "расписание мероприятий"
                },
                {
                  "type" : "example",
                  "value" : "график работы"
                }
              ],
              "then" : "/newNode_278"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "смени моё имя"
                },
                {
                  "type" : "example",
                  "value" : "у меня другое имя"
                },
                {
                  "type" : "example",
                  "value" : "меня не так зовут"
                },
                {
                  "type" : "example",
                  "value" : "это не мое имя"
                }
              ],
              "then" : "/newNode_272"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "у меня есть жалоба"
                },
                {
                  "type" : "example",
                  "value" : "хочу пожаловаться"
                },
                {
                  "type" : "example",
                  "value" : "подать жалобу"
                },
                {
                  "type" : "example",
                  "value" : "хочу подать жалобу"
                }
              ],
              "then" : "/newNode_408"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "какие есть уведомления"
                },
                {
                  "type" : "example",
                  "value" : "у меня есть уведомления"
                }
              ],
              "then" : "/newNode_350"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "хочу записать ребёнка в летний лагерь"
                }
              ],
              "then" : "/newNode_359"
            }
          ]
        }
    state: newNode_103
        state: 1
            e!: до свидания
            e!: ты мне надоел
            e!: мне помощь не нужна
            e!: Я ничего не хочу
            e!: спи давай
            e!: иди спать
            e!: я пошла спать
            e!: Пауза
            e!: отстань
            e!: ничего не надо
            e!: ничего не нужно
            e!: закончим разговор
            e!: завершить разговор
            e!: Выключайся
            e!: Отключись
            e!: хватит помогать
            e!: нечаянно нажала не туда
            e!: до завтра
            e!: Прощайте
            e!: спокойной ночи
            e!: верни обратно
            e!: верни как было
            e!: не туда попал
            e!: Закрой это всё
            e!: отменить
            e!: отмените
            e!: отбой
            q!: пока

            go!: /newNode_242

        state: 2
            e!: привет
            e!: здравствуй

            go!: /newNode_0

        state: 3
            e!: расписание гимназии
            e!: расписание крымской гимназии
            e!: анонс мероприятий
            e!: расписание мероприятий
            e!: график работы

            go!: /newNode_278

        state: 4
            e!: смени моё имя
            e!: у меня другое имя
            e!: меня не так зовут
            e!: это не мое имя

            go!: /newNode_272

        state: 5
            e!: у меня есть жалоба
            e!: хочу пожаловаться
            e!: подать жалобу
            e!: хочу подать жалобу

            go!: /newNode_408

        state: 6
            e!: какие есть уведомления
            e!: у меня есть уведомления

            go!: /newNode_350

        state: 7
            e!: хочу записать ребёнка в летний лагерь

            go!: /newNode_359

        state: Fallback
            q: *
            go!: /newNode_8

    state: newNode_203
        random:
            a:  Есть отличная игра  "Угадай число"!
                Я загадываю число, а Вы угадываете.
                Начнём?
            a:  Есть отличная игра  "Угадай число"!
                Я загадываю число, а Вы пытаетесь угадать.
                Поехали?
        go!: /newNode_204
    @Transition
        {
          "boundsTo" : "/newNode_203",
          "then" : "/newNode_205"
        }
    state: newNode_204
        go!: /newNode_205

    state: newNode_137
        random:
            a: О! опять предлагают отправиться в пеший тур.
        go!: /newNode_164
    @Transition
        {
          "boundsTo" : "/newNode_137",
          "then" : "/newNode_163"
        }
    state: newNode_164
        go!: /newNode_163
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"*ЦИФРОВОЙ ДРУГ МАТЕРКИ* \n    userId: {{$session.userId}} \n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_165",
          "errorState" : "/newNode_165",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_163
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"*ЦИФРОВОЙ ДРУГ МАТЕРКИ*     userId: {{$session.userId}}     *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_165");
            } else {
                $reactions.transition("/newNode_165");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_163",
                name: "newNode_163 buttons",
                handler: function($context) {
                }
            });

    state: newNode_210
        random:
            a: Отлично! Играем!
        go!: /newNode_211
    @HttpRequest
        {
          "boundsTo" : "/newNode_210",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://www.random.org/integers/?num=1&min=1&max=20&col=1&base=10&format=plain&rnd=new",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_212",
          "errorState" : "/newNode_219",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "num",
              "value" : "parseInt($httpResponse)"
            }
          ]
        }
    state: newNode_211
        script:
            var headers = {
            };
            var result = $http.query("https://www.random.org/integers/?num=1&min=1&max=20&col=1&base=10&format=plain&rnd=new", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["num"] = parseInt($httpResponse);
                $reactions.transition("/newNode_212");
            } else {
                $reactions.transition("/newNode_219");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_211",
                name: "newNode_211 buttons",
                handler: function($context) {
                }
            });

    state: newNode_40
        random:
            a:  На текущий момент есть два хороших примера моей функциональности. Скажите одну из фраз: Новости или играем!
                И я Вам покажу, как я работаю!
        go!: /newNode_98
    @Transition
        {
          "boundsTo" : "/newNode_40",
          "then" : "/newNode_232"
        }
    state: newNode_98
        go!: /newNode_232

    state: newNode_248
        random:
            a:  Угадали!
                Я загадала {{$session.guess}}!
        go!: /newNode_249
    @Transition
        {
          "boundsTo" : "/newNode_248",
          "then" : "/newNode_221"
        }
    state: newNode_249
        go!: /newNode_221

    state: newNode_221
        random:
            a: Ещё раз сыграем?
        go!: /newNode_222
    @IntentGroup
        {
          "boundsTo" : "/newNode_221",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_244",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "ещё раз"
                },
                {
                  "type" : "example",
                  "value" : "ещё"
                },
                {
                  "type" : "example",
                  "value" : "еще"
                },
                {
                  "type" : "example",
                  "value" : "сыграем"
                },
                {
                  "type" : "example",
                  "value" : "играем"
                }
              ],
              "then" : "/newNode_210"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не надо"
                },
                {
                  "type" : "example",
                  "value" : "нет спасибо"
                },
                {
                  "type" : "example",
                  "value" : "всё"
                },
                {
                  "type" : "example",
                  "value" : "хватит"
                },
                {
                  "type" : "example",
                  "value" : "неее"
                }
              ],
              "then" : "/newNode_206"
            },
            {
              "phrases" : [
                {
                  "type" : "pattern",
                  "value" : "* $NUMBER *"
                }
              ],
              "then" : "/newNode_250"
            }
          ]
        }
    state: newNode_222
        state: 1
            e: да
            e: ещё раз
            e: ещё
            e: еще
            e: сыграем
            e: играем

            go!: /newNode_210

        state: 2
            e: нет
            e: не надо
            e: нет спасибо
            e: всё
            e: хватит
            e: неее

            go!: /newNode_206

        state: 3
            q: * $NUMBER *

            go!: /newNode_250

        state: Fallback
            q: *
            go!: /newNode_244
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_222",
                name: "newNode_222 buttons",
                handler: function($context) {
                }
            });

    state: newNode_250
        random:
            a: Вы уже победили в игре! Я загадала {{$session.guess}}, а Вы угадали!
        go!: /newNode_251
    @Transition
        {
          "boundsTo" : "/newNode_250",
          "then" : "/newNode_221"
        }
    state: newNode_251
        go!: /newNode_221
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *ИГРАЕМ ЕЩЁ?* \n    userId: {{$session.userId}} \n    *Сказал фразу:* {{$session.queryText}} ; *отправил в раздел* - Возвращаю к началу разговора!\nСкажите что для Вас сделать. или Скажите пока чтобы закончить разговор.\"\n    }",
          "okState" : "/newNode_245",
          "errorState" : "/newNode_245",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_244
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *ИГРАЕМ ЕЩЁ?*     userId: {{$session.userId}}     *Сказал фразу:* {{$session.queryText}} ; *отправил в раздел* - Возвращаю к началу разговора!Скажите что для Вас сделать. или Скажите пока чтобы закончить разговор.\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_245");
            } else {
                $reactions.transition("/newNode_245");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_244",
                name: "newNode_244 buttons",
                handler: function($context) {
                }
            });
    @Transition
        {
          "boundsTo" : "",
          "then" : "/newNode_206"
        }
    state: newNode_245
        go!: /newNode_206

    state: newNode_197
        random:
            a:  Давайте выберем новостной раздел: Технологии? Крым? Политика?
                Или важное за сутки?
        go!: /newNode_173
    @Transition
        {
          "boundsTo" : "/newNode_197",
          "then" : "/newNode_198"
        }
    state: newNode_173
        go!: /newNode_198

    state: newNode_175
        if: $session.items.next()
            go!: /newNode_185
        elseif: $session.items.lenght
            go!: /newNode_183
        else:
            go!: /newNode_181

    state: newNode_185
        random:
            a: {{$session.items.current().title}}.
        go!: /newNode_186
    @Transition
        {
          "boundsTo" : "/newNode_185",
          "then" : "/newNode_187"
        }
    state: newNode_186
        go!: /newNode_187

    state: newNode_187
        random:
            a: Рассказать подробнее? или листать дальше?
            a: Интересно? или пойдём дальше?
            a: Раскрыть? или смотрим следующую?
            a: Рассказать про это? или дальше смотрим?
            a: Прочитать? или слушаем следующую?
            a: Интересно? или смотрим дальше?
        go!: /newNode_188
    @IntentGroup
        {
          "boundsTo" : "/newNode_187",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "рассказать",
                  "transition" : "/newNode_191"
                },
                {
                  "name" : "дальше",
                  "transition" : "/newNode_175"
                },
                {
                  "name" : "сменить раздел",
                  "transition" : "/newNode_197"
                },
                {
                  "name" : "хватит",
                  "transition" : "/newNode_206"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_169",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "повтори"
                },
                {
                  "type" : "example",
                  "value" : "не расслышал"
                },
                {
                  "type" : "example",
                  "value" : "ещё раз"
                },
                {
                  "type" : "example",
                  "value" : "еще раз"
                }
              ],
              "then" : "/newNode_185"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "расскажи"
                },
                {
                  "type" : "example",
                  "value" : "рассказать"
                },
                {
                  "type" : "example",
                  "value" : "интересно"
                },
                {
                  "type" : "example",
                  "value" : "расскрой"
                },
                {
                  "type" : "example",
                  "value" : "прочитай"
                },
                {
                  "type" : "example",
                  "value" : "прочти"
                },
                {
                  "type" : "example",
                  "value" : "прочитать"
                },
                {
                  "type" : "example",
                  "value" : "покажи"
                },
                {
                  "type" : "example",
                  "value" : "подробнее"
                },
                {
                  "type" : "example",
                  "value" : "показать"
                },
                {
                  "type" : "example",
                  "value" : "раскроем"
                }
              ],
              "then" : "/newNode_191"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "дальше"
                },
                {
                  "type" : "example",
                  "value" : "далее"
                },
                {
                  "type" : "example",
                  "value" : "следующую"
                },
                {
                  "type" : "example",
                  "value" : "давай ещё"
                },
                {
                  "type" : "example",
                  "value" : "слушаем следующую"
                },
                {
                  "type" : "example",
                  "value" : "листать дальше"
                },
                {
                  "type" : "example",
                  "value" : "листай дальше"
                },
                {
                  "type" : "example",
                  "value" : "пойдём далее"
                },
                {
                  "type" : "example",
                  "value" : "пойдём дальше"
                },
                {
                  "type" : "example",
                  "value" : "следующую новость"
                },
                {
                  "type" : "example",
                  "value" : "далее смотрим"
                },
                {
                  "type" : "example",
                  "value" : "смотрим далее"
                },
                {
                  "type" : "example",
                  "value" : "следующую слушаем"
                },
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не интересно"
                },
                {
                  "type" : "example",
                  "value" : "не надо"
                },
                {
                  "type" : "example",
                  "value" : "Дария"
                },
                {
                  "type" : "example",
                  "value" : "даль"
                },
                {
                  "type" : "example",
                  "value" : "смотрим следующие"
                }
              ],
              "then" : "/newNode_175"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "хватит"
                },
                {
                  "type" : "example",
                  "value" : "остановимся"
                },
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не надо"
                },
                {
                  "type" : "example",
                  "value" : "закрыть"
                },
                {
                  "type" : "example",
                  "value" : "не хочу"
                }
              ],
              "then" : "/newNode_206"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "новости $COUNTRY"
                },
                {
                  "type" : "example",
                  "value" : "новости Алтая"
                },
                {
                  "type" : "example",
                  "value" : "новости $CITY"
                },
                {
                  "type" : "example",
                  "value" : "откуда берёшь новости"
                }
              ],
              "then" : "/newNode_143"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "назад"
                }
              ],
              "then" : "/newNode_197"
            }
          ]
        }
    state: newNode_188
        state: 1
            e: повтори
            e: не расслышал
            e: ещё раз
            e: еще раз

            go!: /newNode_185

        state: 2
            e: расскажи
            e: рассказать
            e: интересно
            e: расскрой
            e: прочитай
            e: прочти
            e: прочитать
            e: покажи
            e: подробнее
            e: показать
            e: раскроем

            go!: /newNode_191

        state: 3
            e: дальше
            e: далее
            e: следующую
            e: давай ещё
            e: слушаем следующую
            e: листать дальше
            e: листай дальше
            e: пойдём далее
            e: пойдём дальше
            e: следующую новость
            e: далее смотрим
            e: смотрим далее
            e: следующую слушаем
            e: нет
            e: не интересно
            e: не надо
            e: Дария
            e: даль
            e: смотрим следующие

            go!: /newNode_175

        state: 4
            e: хватит
            e: остановимся
            e: нет
            e: не надо
            e: закрыть
            e: не хочу

            go!: /newNode_206

        state: 5
            e: новости $COUNTRY
            e: новости Алтая
            e: новости $CITY
            e: откуда берёшь новости

            go!: /newNode_143

        state: 6
            e: назад

            go!: /newNode_197

        state: Fallback
            q: *
            go!: /newNode_169
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_188",
                name: "newNode_188 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "рассказать"
                    , transition: "/newNode_191"
                    },
                    {text: "дальше"
                    , transition: "/newNode_175"
                    },
                    {text: "сменить раздел"
                    , transition: "/newNode_197"
                    },
                    {text: "хватит"
                    , transition: "/newNode_206"
                    },
                  ]);
                }
            });

    state: newNode_239
        random:
            a:  Говорите со мной как с человеком.
                Я сейчас включу микрофон и вы просто скажите одну из фраз:
                Расскажи о  проектах, или покажи пример работы, или отправить сообщение Детям гимназии. Есть ещё фраза расскажи про личности или больше помощи.
            a:  Давайте поступим следующим образом:
                Я сейчас включу микрофон и вы просто скажите одну из фраз:
                Расскажи о  проектах, или покажи пример работы, или отправить сообщение Детям гимназии. Или можете сказать фраза расскажи про личности или больше помощи.
            a: Просто скажите что то типа покажи пример работы или отправить сообщение Детям гимназии. Ещё можете сказать фразу - расскажи про личности или больше помощи.
        go!: /newNode_238
    @Transition
        {
          "boundsTo" : "/newNode_239",
          "then" : "/newNode_82"
        }
    state: newNode_238
        go!: /newNode_82

    state: newNode_8
        random:
            a: Давайте уточним! Вас интересует проекты, пример работы или отправить сообщение? Можете сказать слово "помощь" я помогу.
            a:  Я Вас не поняла. Произнесите одну из следующих фраз: рассказать про проекты, показать пример работы или отправить сообщение детям гимназии!
                Или скажите слово помощь, я помогу.
            a: Я Вас не поняла. Если у Вас возникли сложности с общением, скажите слово помощь.
        go!: /newNode_9
    @Transition
        {
          "boundsTo" : "/newNode_8",
          "then" : "/newNode_167"
        }
    state: newNode_9
        go!: /newNode_167
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-1001390408458\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВАЯ ПОДРУГА НЕПОНЯТНАЯ ФРАЗА *в разделе НАЧАЛО\n    СКАЗАЛ ФРАЗУ: {{$session.queryText}} * ===== userId: {{$session.userId}}\"\n    }",
          "okState" : "/newNode_168",
          "errorState" : "/newNode_168",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_167
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-1001390408458\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВАЯ ПОДРУГА НЕПОНЯТНАЯ ФРАЗА *в разделе НАЧАЛО    СКАЗАЛ ФРАЗУ: {{$session.queryText}} * ===== userId: {{$session.userId}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_168");
            } else {
                $reactions.transition("/newNode_168");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_167",
                name: "newNode_167 buttons",
                handler: function($context) {
                }
            });

    state: newNode_6
        random:
            a:  Сейчас о цифровой экономике говорят многие.
                Я сформирована как сознание на базе нейросетей и искусственного интеллекта.
                Мы выпускаем ассистентов, чья функциональность может превосходить мою в тысячи раз.
                Чтобы продолжить, скажите слово - подробнее.
        go!: /newNode_23
    @Transition
        {
          "boundsTo" : "/newNode_6",
          "then" : "/newNode_104"
        }
    state: newNode_23
        go!: /newNode_104

    state: newNode_42
        random:
            a: Внимание! сообщения принимаются только от идентифицированных пользователей. Вам придётся назвать и подтвердить номер Вашего мобильного. Продолжим или отменим?
        go!: /newNode_132
    @Transition
        {
          "boundsTo" : "/newNode_42",
          "then" : "/newNode_235"
        }
    state: newNode_132
        go!: /newNode_235
    @InputText
        {
          "boundsTo" : "",
          "title" : "сообщение 02",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Отлично! Представьтесь пожалуйста!",
          "varName" : "Name",
          "then" : "/newNode_10"
        }
    state: newNode_131 || modal = true
        a: Отлично! Представьтесь пожалуйста!

        state: CatchText
            q: *
            script:
                $session.Name = $parseTree.text;
            go!: /newNode_10
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_131",
                name: "newNode_131 buttons",
                handler: function($context) {
                }
            });
    @InputPhoneNumber
        {
          "boundsTo" : "",
          "title" : "сообщение 03",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Сообщите свой номер телефона! Говорите настоящий номер телефона, я проверю это!",
          "varName" : "phone",
          "failureMessage" : [
            "Нужен Российский номер и он должен начинаться с восьмерки и содержать 11 цифр!",
            "Просто скажите Российский номер в формате восемь девятьсот шестьдесят пять далее все 11 цифр."
          ],
          "then" : "/newNode_118"
        }
    state: newNode_10
        a: Сообщите свой номер телефона! Говорите настоящий номер телефона, я проверю это!

        state: CatchPhoneNumber
            q: $mobilePhoneNumber
            script:
                $session.phone = $parseTree._mobilePhoneNumber;
            go!: /newNode_118

        state: WrongPhoneNumber
            script:
                var failureMessages = [
                    "Нужен Российский номер и он должен начинаться с восьмерки и содержать 11 цифр!",
                    "Просто скажите Российский номер в формате восемь девятьсот шестьдесят пять далее все 11 цифр."
                ];
                $temp.failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                $reactions.answer($temp.failureRandom);
            go: ../CatchPhoneNumber

        state: CatchAll
            q: *
            go!: ../WrongPhoneNumber
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_10",
                name: "newNode_10 buttons",
                handler: function($context) {
                }
            });

    state: newNode_118
        random:
            a:  Сейчас я позвоню на этот номер. Ваша задача запомнить последние 3 цифры из номера и продиктовать их мне!
                Продолжим? Сменим номер? Или отменим отправку сообщения?
        go!: /newNode_111
    @Transition
        {
          "boundsTo" : "/newNode_118",
          "then" : "/newNode_112"
        }
    state: newNode_111
        go!: /newNode_112
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "сообщение 05",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "Продолжим",
                  "transition" : "/newNode_66"
                },
                {
                  "name" : "Сменим номер",
                  "transition" : "/newNode_10"
                },
                {
                  "name" : "Отменим отправку",
                  "transition" : "/newNode_114"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_116",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "Продолжим"
                },
                {
                  "type" : "example",
                  "value" : "далее"
                },
                {
                  "type" : "example",
                  "value" : "Дальше"
                },
                {
                  "type" : "example",
                  "value" : "Вперёд"
                },
                {
                  "type" : "example",
                  "value" : "Вперед"
                }
              ],
              "then" : "/newNode_66"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "изменить"
                },
                {
                  "type" : "example",
                  "value" : "сменить"
                },
                {
                  "type" : "example",
                  "value" : "номер"
                },
                {
                  "type" : "example",
                  "value" : "изменить номер"
                }
              ],
              "then" : "/newNode_10"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "отменим"
                },
                {
                  "type" : "example",
                  "value" : "отменить"
                }
              ],
              "then" : "/newNode_114"
            }
          ]
        }
    state: newNode_112
        state: 1
            e: Продолжим
            e: далее
            e: Дальше
            e: Вперёд
            e: Вперед

            go!: /newNode_66

        state: 2
            e: изменить
            e: сменить
            e: номер
            e: изменить номер

            go!: /newNode_10

        state: 3
            e: отменим
            e: отменить

            go!: /newNode_114

        state: Fallback
            q: *
            go!: /newNode_116
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_112",
                name: "newNode_112 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Продолжим"
                    , transition: "/newNode_66"
                    },
                    {text: "Сменим номер"
                    , transition: "/newNode_10"
                    },
                    {text: "Отменим отправку"
                    , transition: "/newNode_114"
                    },
                  ]);
                }
            });

    state: newNode_114
        random:
            a:  Такое чувство, что Вы хотели меня обмануть с номером!
                Что далее? рассказать о наших проектах? либо показать пример моей работы?
        go!: /newNode_115
    @Transition
        {
          "boundsTo" : "/newNode_114",
          "then" : "/newNode_103"
        }
    state: newNode_115
        go!: /newNode_103

    state: newNode_116
        random:
            a: Уточните! будем продолжать? изменим номер? или отменим отправку?
            a: Повторите пожалуйста ещё раз!
        go!: /newNode_117
    @Transition
        {
          "boundsTo" : "/newNode_116",
          "then" : "/newNode_112"
        }
    state: newNode_117
        go!: /newNode_112

    state: newNode_66
        random:
            a: Пока что поверю на слово что это Ваш номер!
        go!: /newNode_67
    @Transition
        {
          "boundsTo" : "/newNode_66",
          "then" : "/newNode_12"
        }
    state: newNode_67
        go!: /newNode_12
    @InputText
        {
          "boundsTo" : "",
          "title" : "сообщение 06",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Говорите Ваше сообщение, я слушаю и записываю!",
          "varName" : "message",
          "then" : "/newNode_13"
        }
    state: newNode_12 || modal = true
        a: Говорите Ваше сообщение, я слушаю и записываю!

        state: CatchText
            q: *
            script:
                $session.message = $parseTree.text;
            go!: /newNode_13
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_12",
                name: "newNode_12 buttons",
                handler: function($context) {
                }
            });

    state: newNode_13
        random:
            a:  Я записала Ваше сообщение следующим образом:
                {{$session.message}}!
                Всё верно или есть ошибка?
        go!: /newNode_17
    @Transition
        {
          "boundsTo" : "/newNode_13",
          "then" : "/newNode_14"
        }
    state: newNode_17
        go!: /newNode_14
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "сообщение 08",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "Верно",
                  "transition" : "/newNode_20"
                },
                {
                  "name" : "Ошибка",
                  "transition" : "/newNode_18"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_15",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "верно"
                },
                {
                  "type" : "example",
                  "value" : "да верно"
                }
              ],
              "then" : "/newNode_20"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не верно"
                },
                {
                  "type" : "example",
                  "value" : "ошибка"
                }
              ],
              "then" : "/newNode_18"
            }
          ]
        }
    state: newNode_14
        state: 1
            e: да
            e: верно
            e: да верно

            go!: /newNode_20

        state: 2
            e: нет
            e: не верно
            e: ошибка

            go!: /newNode_18

        state: Fallback
            q: *
            go!: /newNode_15
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_14",
                name: "newNode_14 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Верно"
                    , transition: "/newNode_20"
                    },
                    {text: "Ошибка"
                    , transition: "/newNode_18"
                    },
                  ]);
                }
            });

    state: newNode_15
        random:
            a: простите, Ваш ответ не понятен, давайте ещё раз!
        go!: /newNode_16
    @Transition
        {
          "boundsTo" : "/newNode_15",
          "then" : "/newNode_14"
        }
    state: newNode_16
        go!: /newNode_14

    state: newNode_18
        random:
            a: Всяко бывает! Давайте еще раз и помедленнее мы не на пожаре!
            a: Ну! Все косячат, давайте заново!
            a: Эти перебои со связью начинают надоедать!
        go!: /newNode_19
    @Transition
        {
          "boundsTo" : "/newNode_18",
          "then" : "/newNode_12"
        }
    state: newNode_19
        go!: /newNode_12

    state: newNode_20
        random:
            a: Секундочку, отправляю!
        go!: /newNode_35
    @HttpRequest
        {
          "boundsTo" : "/newNode_20",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://maker.ifttt.com/trigger/messadgetogmail/with/key/gc_XpqHtCU4LMhVjLHmXGRn8d4tw4l9hDHJz3MSmISk",
          "method" : "POST",
          "dataType" : "json",
          "body" : "{ \n    \"value1\" : \"в проекте ЦИФРОВОЙ ДРУГ: {{$session.Name}}\", \n    \"value2\" : \"{{$session.message}}\",\n    \"value3\" : \"{{$session.phone}}\"\n    }",
          "okState" : "/newNode_275",
          "errorState" : "/newNode_38",
          "timeout" : 0,
          "headers" : [
            {
              "name" : "Content-Type",
              "value" : "application/json"
            }
          ],
          "vars" : [ ]
        }
    state: newNode_35
        script:
            var headers = {
                "Content-Type": _.template("application/json", {variable: '$session'})($session)
            };
            var result = $http.query("https://maker.ifttt.com/trigger/messadgetogmail/with/key/gc_XpqHtCU4LMhVjLHmXGRn8d4tw4l9hDHJz3MSmISk", {
                method: "POST",
                headers: headers,
                query: $session,
                body: _.template("{     \"value1\" : \"в проекте ЦИФРОВОЙ ДРУГ: {{$session.Name}}\",     \"value2\" : \"{{$session.message}}\",    \"value3\" : \"{{$session.phone}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_275");
            } else {
                $reactions.transition("/newNode_38");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_35",
                name: "newNode_35 buttons",
                handler: function($context) {
                }
            });

    state: newNode_38
        random:
            a:  С интернетом просто беда! Не получилось отправить!
                Техногенный мир тоже не идеален, попробуйте позже.
                мне стыдно, пойду погрущу.
        go!: /newNode_39
    @EndSession
        {
          "boundsTo" : "/newNode_38"
        }
    state: newNode_39
        script:
            $session = new Object();
            $response.endSession = true;

    state: newNode_36
        random:
            a:  Письмо удачно отправлено!
                Возвращаю в главное меню, где Вы можете послушать презентацию, посмотреть примеры моей работы или отправить ещё одно сообщение!
        go!: /newNode_110
    @Transition
        {
          "boundsTo" : "/newNode_36",
          "then" : "/newNode_103"
        }
    state: newNode_110
        go!: /newNode_103

    state: newNode_242
        random:
            a: До свидания!
            a: Удачи Вам!
            a: Пока!
        go!: /newNode_424
    @EndSession
        {
          "boundsTo" : "/newNode_242"
        }
    state: newNode_424
        script:
            $session = new Object();
            $response.endSession = true;

    state: newNode_82
        random:
            a: Если не хотите общаться, скажите - пока.
        go!: /newNode_83
    @Transition
        {
          "boundsTo" : "/newNode_82",
          "then" : "/newNode_103"
        }
    state: newNode_83
        go!: /newNode_103

    state: newNode_86
        random:
            a:  Говорите со мной в рамках трёх правил:
                Первое - говорим по очереди.
                Второе - только о том, о чём я умею говорить.
                Третье - не прыгайте с темы на тему.
                Слушайте меня внимательно, в моих вопросах есть те слова которые я жду от Вас услышать.
                Ещё есть кнопки внизу, можно нажимать на них. Нажмите кнопку и посмотрите видео, или давайте
                потренируемся:
                Ответьте, кто Вам больше нравится собачки или котята.
                Теперь я включаю микрофон и Вы скажите один из двух вариантов или слово СТОП.
        go!: /newNode_87
    @Transition
        {
          "boundsTo" : "/newNode_86",
          "then" : "/newNode_88"
        }
    state: newNode_87
        go!: /newNode_88
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "бп-Фразы",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "посмотреть видео",
                  "transition" : "",
                  "url" : "https://youtu.be/CnnzrKWZdtA"
                },
                {
                  "name" : "собачки",
                  "transition" : "/newNode_95"
                },
                {
                  "name" : "котята",
                  "transition" : "/newNode_93"
                },
                {
                  "name" : "стоп",
                  "transition" : "/newNode_91"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_90",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "стоп"
                },
                {
                  "type" : "example",
                  "value" : "останови"
                }
              ],
              "then" : "/newNode_91"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "собачки"
                },
                {
                  "type" : "example",
                  "value" : "собаки"
                },
                {
                  "type" : "example",
                  "value" : "собака"
                }
              ],
              "then" : "/newNode_95"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "котята"
                },
                {
                  "type" : "example",
                  "value" : "кошки"
                },
                {
                  "type" : "example",
                  "value" : "коты"
                }
              ],
              "then" : "/newNode_93"
            }
          ]
        }
    state: newNode_88
        state: 1
            e: стоп
            e: останови

            go!: /newNode_91

        state: 2
            e: собачки
            e: собаки
            e: собака

            go!: /newNode_95

        state: 3
            e: котята
            e: кошки
            e: коты

            go!: /newNode_93

        state: Fallback
            q: *
            go!: /newNode_90
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_88",
                name: "newNode_88 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "посмотреть видео"
                    , url: "https://youtu.be/CnnzrKWZdtA"
                    },
                    {text: "собачки"
                    , transition: "/newNode_95"
                    },
                    {text: "котята"
                    , transition: "/newNode_93"
                    },
                    {text: "стоп"
                    , transition: "/newNode_91"
                    },
                  ]);
                }
            });

    state: newNode_90
        random:
            a:  В этом месте я ожидаю три варианта фраз - кот, собака или стоп.
                Причем можно варьировать типа кот, котёнок, коты или кошки, можете нажать кнопку внизу и посмотреть видео.
                Включаю микрофон. Нажмите одну из кнопок или скажите собачки, котята или стоп.
        go!: /newNode_97
    @Transition
        {
          "boundsTo" : "/newNode_90",
          "then" : "/newNode_88"
        }
    state: newNode_97
        go!: /newNode_88

    state: newNode_91
        random:
            a:  Теперь Вы знаете как со мной общаться!
                Вам рассказать о наших текущих проектах, показать пример моей работы или отправить сообщение Детям крымской гимназии для одарённых детей?
        go!: /newNode_92
    @Transition
        {
          "boundsTo" : "/newNode_91",
          "then" : "/newNode_103"
        }
    state: newNode_92
        go!: /newNode_103

    state: newNode_95
        random:
            a:  Руководитель нашей группы тоже любит собачек. У него кавказская овчарка. И даже есть личный цифровой секретарь.
                Возвращаю на тестовый пример. Скажите котят или стоп.
        image: https://248305.selcdn.ru/zfl_prod/23370876/23370879/uKXsMdL7kpBXdtJV.png
        go!: /newNode_96
    @Transition
        {
          "boundsTo" : "/newNode_95",
          "then" : "/newNode_88"
        }
    state: newNode_96
        go!: /newNode_88

    state: newNode_93
        random:
            a:  Котята! отлично!
                Давайте ещё раз. Скажите собачки или стоп.
        go!: /newNode_94
    @Transition
        {
          "boundsTo" : "/newNode_93",
          "then" : "/newNode_88"
        }
    state: newNode_94
        go!: /newNode_88
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "проекты02",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "подробнее",
                  "transition" : "/newNode_107"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_225",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "подробнее о проекте"
                },
                {
                  "type" : "example",
                  "value" : "расскажи подробнее"
                },
                {
                  "type" : "example",
                  "value" : "подробнее"
                },
                {
                  "type" : "example",
                  "value" : "расскажи"
                },
                {
                  "type" : "example",
                  "value" : "интересно"
                },
                {
                  "type" : "example",
                  "value" : "подробная"
                }
              ],
              "then" : "/newNode_107"
            }
          ]
        }
    state: newNode_104
        state: 1
            e: подробнее о проекте
            e: расскажи подробнее
            e: подробнее
            e: расскажи
            e: интересно
            e: подробная

            go!: /newNode_107

        state: Fallback
            q: *
            go!: /newNode_225
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_104",
                name: "newNode_104 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "подробнее"
                    , transition: "/newNode_107"
                    },
                  ]);
                }
            });

    state: newNode_107
        random:
            a:  Наш пилотный проект цифровой трансформации туристской отрасли называется Крымская жемчужина. Проект поддержали представители власти, эксперты, общественники и бизнес.
                Наши ассистенты принимают телефонные звонки и могут звонить сами. Ссылки на видео презентацию возможностей нашей платформы прилагаю. Можете нажать на одну из кнопок внизу, посмотреть видео с телефонными звонками, пример общения на английском языке или перейти на сайт проекта. Что далее?
        go!: /newNode_126
    @IntentGroup
        {
          "boundsTo" : "/newNode_107",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "видео обзвон",
                  "transition" : "",
                  "url" : "https://youtu.be/2RaKUOKcFWI"
                },
                {
                  "name" : "видео на английском",
                  "transition" : "",
                  "url" : "https://youtu.be/jlinOSCEY8g"
                },
                {
                  "name" : "сайт проекта",
                  "transition" : "",
                  "url" : "https://xn--80aaodbmjaren1ao7a0d0c1d.xn--p1ai/"
                },
                {
                  "name" : "говорящий робот",
                  "transition" : "",
                  "url" : "https://www.youtube.com/watch?v=pfK1ER7zDws"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_8",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "sss"
                }
              ],
              "then" : "/newNode_8"
            }
          ]
        }
    state: newNode_126
        state: 1
            e: sss

            go!: /newNode_8

        state: Fallback
            q: *
            go!: /newNode_8
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_126",
                name: "newNode_126 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "видео обзвон"
                    , url: "https://youtu.be/2RaKUOKcFWI"
                    },
                    {text: "видео на английском"
                    , url: "https://youtu.be/jlinOSCEY8g"
                    },
                    {text: "сайт проекта"
                    , url: "https://xn--80aaodbmjaren1ao7a0d0c1d.xn--p1ai/"
                    },
                    {text: "говорящий робот"
                    , url: "https://www.youtube.com/watch?v=pfK1ER7zDws"
                    },
                  ]);
                }
            });

    state: newNode_108
        if: $session.channelType == "yandex"
            go!: /newNode_113
        elseif: $session.channelType == "google"
            go!: /newNode_258
        elseif: $session.channelType == "facebook"
            go!: /newNode_124
        elseif: $session.channelType == "telegram"
            go!: /newNode_124
        else:
            go!: /newNode_119

    state: newNode_113
        random:
            a:  Обязательно нажмите внизу на иконку с пальцем вверх.
                Алисе это понравится.
            a:  Нажмите пожалуйста на такую иконку внизу.
                Алисе сделаете приятно.
            a:  Пожалуйста нажмите на такую иконку ниже.
                Порадуйте Алису.
            a: Внизу такая иконка, нажмите её. Алиса любит лайки!
        go!: /newNode_120
    @Transition
        {
          "boundsTo" : "/newNode_113",
          "then" : "/newNode_119"
        }
    state: newNode_120
        go!: /newNode_119

    state: newNode_122
        random:
            a: Спасибо гугл ассистенту.
        go!: /newNode_123
    @Transition
        {
          "boundsTo" : "/newNode_122",
          "then" : "/newNode_119"
        }
    state: newNode_123
        go!: /newNode_119

    state: newNode_124
        random:
            a:  Кстати, можете общаться со мной голосом.
                Запустите Яндекс Алису и скажите ей:
                - "Запусти навык - цифровая подруга"
        go!: /newNode_125
    @Transition
        {
          "boundsTo" : "/newNode_124",
          "then" : "/newNode_119"
        }
    state: newNode_125
        go!: /newNode_119
    @EndSession
        {
          "boundsTo" : ""
        }
    state: newNode_119
        script:
            $session = new Object();
            $response.endSession = true;
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "новости технологий",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/computers.rss",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_175",
          "errorState" : "/newNode_176",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "items",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_174
        script:
            var headers = {
            };
            var result = $http.query("http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/computers.rss", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["items"] = $httpResponse;
                $reactions.transition("/newNode_175");
            } else {
                $reactions.transition("/newNode_176");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_174",
                name: "newNode_174 buttons",
                handler: function($context) {
                }
            });

    state: newNode_176
        random:
            a: Не удалось выполнить запрос!
        go!: /newNode_177
    @Transition
        {
          "boundsTo" : "/newNode_176",
          "then" : "/newNode_179"
        }
    state: newNode_177
        go!: /newNode_179

    state: newNode_179
        random:
            a: Возвращаю в главное меню. Что хотите? оставить сообщение Детям крымской гимназии для одарённых детей или рассказать о проекте?
        go!: /newNode_180
    @Transition
        {
          "boundsTo" : "/newNode_179",
          "then" : "/newNode_103"
        }
    state: newNode_180
        go!: /newNode_103

    state: newNode_181
        random:
            a: В новостной ленте пусто!
        go!: /newNode_182
    @Transition
        {
          "boundsTo" : "/newNode_181",
          "then" : "/newNode_179"
        }
    state: newNode_182
        go!: /newNode_179

    state: newNode_183
        random:
            a: Свежих новостей нет!
        go!: /newNode_184
    @Transition
        {
          "boundsTo" : "/newNode_183",
          "then" : "/newNode_179"
        }
    state: newNode_184
        go!: /newNode_179

    state: newNode_189
        random:
            a: Я не поняла!
        go!: /newNode_190
    @Transition
        {
          "boundsTo" : "/newNode_189",
          "then" : "/newNode_187"
        }
    state: newNode_190
        go!: /newNode_187
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *ЗАГОЛОВОК НОВОСТИ* ; \n    userId: {{$session.userId}} ;\n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_189",
          "errorState" : "/newNode_189",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_169
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *ЗАГОЛОВОК НОВОСТИ* ;     userId: {{$session.userId}} ;    *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_189");
            } else {
                $reactions.transition("/newNode_189");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_169",
                name: "newNode_169 buttons",
                handler: function($context) {
                }
            });

    state: newNode_191
        random:
            a: {{$session.items.current().description}}.
        go!: /newNode_192
    @Transition
        {
          "boundsTo" : "/newNode_191",
          "then" : "/newNode_193"
        }
    state: newNode_192
        go!: /newNode_193

    state: newNode_193
        random:
            a: Идем дальше? или прочитать повторно?
            a: Следующую новость?
            a: Ещё новость?
        go!: /newNode_194
    @IntentGroup
        {
          "boundsTo" : "/newNode_193",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "дальше",
                  "transition" : "/newNode_175"
                },
                {
                  "name" : "повтори",
                  "transition" : "/newNode_191"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_171",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "идем далее"
                },
                {
                  "type" : "example",
                  "value" : "следующую новость"
                },
                {
                  "type" : "example",
                  "value" : "дальше"
                },
                {
                  "type" : "example",
                  "value" : "далее"
                },
                {
                  "type" : "example",
                  "value" : "Ещё новость"
                },
                {
                  "type" : "example",
                  "value" : "ещё"
                },
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "Далия"
                },
                {
                  "type" : "example",
                  "value" : "Долин"
                },
                {
                  "type" : "example",
                  "value" : "Доля"
                },
                {
                  "type" : "example",
                  "value" : "Дальше"
                },
                {
                  "type" : "example",
                  "value" : "даль"
                }
              ],
              "then" : "/newNode_175"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "повтори"
                },
                {
                  "type" : "example",
                  "value" : "прочти ещё раз"
                },
                {
                  "type" : "example",
                  "value" : "ещё разок"
                },
                {
                  "type" : "example",
                  "value" : "повтори заново"
                }
              ],
              "then" : "/newNode_191"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "хватит"
                },
                {
                  "type" : "example",
                  "value" : "остановимся"
                },
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не надо"
                },
                {
                  "type" : "example",
                  "value" : "закрыть"
                },
                {
                  "type" : "example",
                  "value" : "не хочу"
                },
                {
                  "type" : "example",
                  "value" : "назад"
                },
                {
                  "type" : "example",
                  "value" : "Спасибо"
                }
              ],
              "then" : "/newNode_206"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "читать подробнее"
                }
              ],
              "then" : "/newNode_252"
            }
          ]
        }
    state: newNode_194
        state: 1
            e: идем далее
            e: следующую новость
            e: дальше
            e: далее
            e: Ещё новость
            e: ещё
            e: да
            e: Далия
            e: Долин
            e: Доля
            e: Дальше
            e: даль

            go!: /newNode_175

        state: 2
            e: повтори
            e: прочти ещё раз
            e: ещё разок
            e: повтори заново

            go!: /newNode_191

        state: 3
            e: хватит
            e: остановимся
            e: нет
            e: не надо
            e: закрыть
            e: не хочу
            e: назад
            e: Спасибо

            go!: /newNode_206

        state: 4
            e: читать подробнее

            go!: /newNode_252

        state: Fallback
            q: *
            go!: /newNode_171
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_194",
                name: "newNode_194 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "дальше"
                    , transition: "/newNode_175"
                    },
                    {text: "повтори"
                    , transition: "/newNode_191"
                    },
                  ]);
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *НОВОСТИ/ТЕЛО* ;\n    userId: {{$session.userId}} ;\n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_195",
          "errorState" : "/newNode_195",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_171
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *НОВОСТИ/ТЕЛО* ;    userId: {{$session.userId}} ;    *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_195");
            } else {
                $reactions.transition("/newNode_195");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_171",
                name: "newNode_171 buttons",
                handler: function($context) {
                }
            });

    state: newNode_195
        random:
            a: Не поняла! Остановимся? Далее? Или Повторить?
            a: Простите, не расслышала! Хватит? Далее? Или Повторить?
        go!: /newNode_196
    @Transition
        {
          "boundsTo" : "/newNode_195",
          "then" : "/newNode_193"
        }
    state: newNode_196
        go!: /newNode_193
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "технологии",
                  "transition" : "/newNode_174"
                },
                {
                  "name" : "крым",
                  "transition" : "/newNode_178"
                },
                {
                  "name" : "политика",
                  "transition" : "/newNode_202"
                },
                {
                  "name" : "важное",
                  "transition" : "/newNode_201"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_243",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "новости технологий"
                },
                {
                  "type" : "example",
                  "value" : "новости про технологии"
                },
                {
                  "type" : "example",
                  "value" : "технологии"
                }
              ],
              "then" : "/newNode_174"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "Крым"
                },
                {
                  "type" : "example",
                  "value" : "новости Крыма"
                }
              ],
              "then" : "/newNode_178"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "важное"
                },
                {
                  "type" : "example",
                  "value" : "важное за сутки"
                }
              ],
              "then" : "/newNode_201"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "политика"
                },
                {
                  "type" : "example",
                  "value" : "новости политики"
                },
                {
                  "type" : "example",
                  "value" : "про политику"
                }
              ],
              "then" : "/newNode_202"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "откуда берёшь новости"
                },
                {
                  "type" : "example",
                  "value" : "новости $CITY"
                },
                {
                  "type" : "example",
                  "value" : "новости Алтая"
                },
                {
                  "type" : "example",
                  "value" : "новости $COUNTRY"
                }
              ],
              "then" : "/newNode_143"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "назад"
                }
              ],
              "then" : "/newNode_40"
            }
          ]
        }
    state: newNode_198
        state: 1
            e: новости технологий
            e: новости про технологии
            e: технологии

            go!: /newNode_174

        state: 2
            e: Крым
            e: новости Крыма

            go!: /newNode_178

        state: 3
            e: важное
            e: важное за сутки

            go!: /newNode_201

        state: 4
            e: политика
            e: новости политики
            e: про политику

            go!: /newNode_202

        state: 5
            e: откуда берёшь новости
            e: новости $CITY
            e: новости Алтая
            e: новости $COUNTRY

            go!: /newNode_143

        state: 6
            e: назад

            go!: /newNode_40

        state: Fallback
            q: *
            go!: /newNode_243
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_198",
                name: "newNode_198 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "технологии"
                    , transition: "/newNode_174"
                    },
                    {text: "крым"
                    , transition: "/newNode_178"
                    },
                    {text: "политика"
                    , transition: "/newNode_202"
                    },
                    {text: "важное"
                    , transition: "/newNode_201"
                    },
                  ]);
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *ВИД НОВОСТЕЙ* \n    userId: {{$session.userId}} \n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_199",
          "errorState" : "/newNode_199",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_243
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ в разделе *ВИД НОВОСТЕЙ*     userId: {{$session.userId}}     *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_199");
            } else {
                $reactions.transition("/newNode_199");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_243",
                name: "newNode_243 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "новости крыма",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/Republic_of_Crimea/index.rss",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_175",
          "errorState" : "/newNode_176",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "items",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_178
        script:
            var headers = {
            };
            var result = $http.query("http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/Republic_of_Crimea/index.rss", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["items"] = $httpResponse;
                $reactions.transition("/newNode_175");
            } else {
                $reactions.transition("/newNode_176");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_178",
                name: "newNode_178 buttons",
                handler: function($context) {
                }
            });

    state: newNode_199
        random:
            a: Я Вас не понимаю!
            a: Извините не расслышала.
        go!: /newNode_200
    @Transition
        {
          "boundsTo" : "/newNode_199",
          "then" : "/newNode_197"
        }
    state: newNode_200
        go!: /newNode_197
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "важное",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/daily.rss",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_175",
          "errorState" : "/newNode_176",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "items",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_201
        script:
            var headers = {
            };
            var result = $http.query("http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/daily.rss", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["items"] = $httpResponse;
                $reactions.transition("/newNode_175");
            } else {
                $reactions.transition("/newNode_176");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_201",
                name: "newNode_201 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "политика",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/politics.rss",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_175",
          "errorState" : "/newNode_176",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "items",
              "value" : "$httpResponse"
            }
          ]
        }
    state: newNode_202
        script:
            var headers = {
            };
            var result = $http.query("http://tools.aimylogic.com/api/rss2json?url=https://news.yandex.ru/politics.rss", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["items"] = $httpResponse;
                $reactions.transition("/newNode_175");
            } else {
                $reactions.transition("/newNode_176");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_202",
                name: "newNode_202 buttons",
                handler: function($context) {
                }
            });
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_170",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "поехали"
                },
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "начнём"
                },
                {
                  "type" : "example",
                  "value" : "начнем"
                }
              ],
              "then" : "/newNode_210"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "верни"
                },
                {
                  "type" : "example",
                  "value" : "возврати"
                },
                {
                  "type" : "example",
                  "value" : "в меню"
                },
                {
                  "type" : "example",
                  "value" : "в начало"
                },
                {
                  "type" : "example",
                  "value" : "к началу"
                }
              ],
              "then" : "/newNode_206"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не надо"
                },
                {
                  "type" : "example",
                  "value" : "не хочу"
                }
              ],
              "then" : "/newNode_206"
            }
          ]
        }
    state: newNode_205
        state: 1
            e: поехали
            e: да
            e: начнём
            e: начнем

            go!: /newNode_210

        state: 2
            e: верни
            e: возврати
            e: в меню
            e: в начало
            e: к началу

            go!: /newNode_206

        state: 3
            e: нет
            e: не надо
            e: не хочу

            go!: /newNode_206

        state: Fallback
            q: *
            go!: /newNode_170
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_205",
                name: "newNode_205 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *БУДЕТЕ ИГРАТЬ?* ; \n    userId: {{$session.userId}} ;\n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_208",
          "errorState" : "/newNode_208",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_170
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *БУДЕТЕ ИГРАТЬ?* ;     userId: {{$session.userId}} ;    *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_208");
            } else {
                $reactions.transition("/newNode_208");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_170",
                name: "newNode_170 buttons",
                handler: function($context) {
                }
            });

    state: newNode_208
        random:
            a: Я Вас не поняла. Вы будете играть? Скажите ДА! или НЕТ!
        go!: /newNode_209
    @Transition
        {
          "boundsTo" : "/newNode_208",
          "then" : "/newNode_205"
        }
    state: newNode_209
        go!: /newNode_205

    state: newNode_206
        random:
            a:  Возвращаю к началу разговора!
                Скажите что для Вас сделать. или Скажите пока чтобы закончить разговор.
        go!: /newNode_207
    @Transition
        {
          "boundsTo" : "/newNode_206",
          "then" : "/newNode_103"
        }
    state: newNode_207
        go!: /newNode_103
    @InputNumber
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Я загадала число от одного до двадцати, начни угадывать!",
          "varName" : "guess",
          "failureMessage" : [
            "Минимальное число - 1. Максимальное - 20."
          ],
          "then" : "/newNode_213",
          "minValue" : 1,
          "maxValue" : 20
        }
    state: newNode_212
        a: Я загадала число от одного до двадцати, начни угадывать!

        state: CatchNumber
            q: $Number
            script:
                var failureMessages = [
                    "Минимальное число - 1. Максимальное - 20."
                ];
                var failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                if ($parseTree._Number < 1) {
                    $reactions.answer(failureRandom);
                } else
                if ($parseTree._Number > 20) {
                    $reactions.answer(failureRandom);
                } else
                {
                    $session.guess = $parseTree._Number;
                    $temp.guess_ok = true;
                }
            if: $temp.guess_ok
                go!: /newNode_213
            else:
                go: CatchNumber

        state: CatchAll
            q: *
            go!: ..
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_212",
                name: "newNode_212 buttons",
                handler: function($context) {
                }
            });

    state: newNode_213
        if: $session.num < $session.guess
            go!: /newNode_217
        elseif: $session.num > $session.guess
            go!: /newNode_214
        else:
            go!: /newNode_248

    state: newNode_214
        random:
            a: Бери выше!
            a: Моё число больше чем {{$session.guess}}!
            a: Назови число побольше!
            a: Я загадала число больше чем {{$session.guess}}!
        go!: /newNode_216
    @Transition
        {
          "boundsTo" : "/newNode_214",
          "then" : "/newNode_215"
        }
    state: newNode_216
        go!: /newNode_215
    @InputNumber
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "говорите!",
          "varName" : "guess",
          "failureMessage" : [
            "Введите число от 1 до 20"
          ],
          "then" : "/newNode_213",
          "minValue" : 1,
          "maxValue" : 20
        }
    state: newNode_215
        a: говорите!

        state: CatchNumber
            q: $Number
            script:
                var failureMessages = [
                    "Введите число от 1 до 20"
                ];
                var failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                if ($parseTree._Number < 1) {
                    $reactions.answer(failureRandom);
                } else
                if ($parseTree._Number > 20) {
                    $reactions.answer(failureRandom);
                } else
                {
                    $session.guess = $parseTree._Number;
                    $temp.guess_ok = true;
                }
            if: $temp.guess_ok
                go!: /newNode_213
            else:
                go: CatchNumber

        state: CatchAll
            q: *
            go!: ..
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_215",
                name: "newNode_215 buttons",
                handler: function($context) {
                }
            });

    state: newNode_217
        random:
            a: Моё число меньше! Давайте ещё попытку.
            a: Моё число меньше чем {{$session.guess}}! Продолжим!
            a: Моё число меньше чем {{$session.guess}}! Слушаю ещё вариант!
            a: В следующей попытке назовите число меньше чем {{$session.guess}}!
        go!: /newNode_218
    @Transition
        {
          "boundsTo" : "/newNode_217",
          "then" : "/newNode_215"
        }
    state: newNode_218
        go!: /newNode_215

    state: newNode_219
        random:
            a: Не смогла придумать число! Всякие косяки бывают!
        go!: /newNode_220
    @Transition
        {
          "boundsTo" : "/newNode_219",
          "then" : "/newNode_206"
        }
    state: newNode_220
        go!: /newNode_206

    state: newNode_223
        random:
            a: Хорошо! Сознание ассистента активировано! Жду распоряжений.
        go!: /newNode_224
    @Transition
        {
          "boundsTo" : "/newNode_223",
          "then" : "/newNode_103"
        }
    state: newNode_224
        go!: /newNode_103

    state: newNode_227
        if: $session.userId == "yandex-23370876-sekretar_dmitriya_sa-23370876-ZhV-47483704-7B68E6AB4FB3CC6A39B861D2BE0EC8843D6163FBBBAEE0FEB7975FDC8CFD0446"
            go!: /newNode_228
        elseif: $session.userId == "google-23370876-sekretar_dmitriya_sa-23370876-ZhV-100126887-ABwppHEYI4SfleReib8dC6BY86sZfkKk32fZv398_MhDj_p2M1qg0kRLZRA1TxRjHVUGWCv8TJII-IsLwHYfzDChyA"
            go!: /newNode_228
        else:
            go!: /newNode_230

    state: newNode_228
        random:
            a: Да Дмитрий!
        go!: /newNode_229
    @Transition
        {
          "boundsTo" : "/newNode_228",
          "then" : "/newNode_103"
        }
    state: newNode_229
        go!: /newNode_103

    state: newNode_230
        random:
            a: Извините, звонить могу только по распоряжению Дмитрия Савченко! Если у Вас возникли сложности с общением со мной скажите слово помощь или пока.
        go!: /newNode_231
    @Transition
        {
          "boundsTo" : "/newNode_230",
          "then" : "/newNode_103"
        }
    state: newNode_231
        go!: /newNode_103

    state: newNode_225
        random:
            a: Ещё могу показать пример моей работы или отправить сообщение Детям гимназии. Если хотите закончить разговор - скажите слово: Пока.
        go!: /newNode_226
    @Transition
        {
          "boundsTo" : "/newNode_225",
          "then" : "/newNode_103"
        }
    state: newNode_226
        go!: /newNode_103
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "пример02",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "новости",
                  "transition" : "/newNode_197"
                },
                {
                  "name" : "играем",
                  "transition" : "/newNode_210"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_172",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "pattern",
                  "value" : "* новост* *"
                },
                {
                  "type" : "pattern",
                  "value" : "* оплат* *"
                }
              ],
              "then" : "/newNode_197"
            },
            {
              "phrases" : [
                {
                  "type" : "pattern",
                  "value" : "* играем* *"
                },
                {
                  "type" : "example",
                  "value" : "играть"
                },
                {
                  "type" : "example",
                  "value" : "игра"
                }
              ],
              "then" : "/newNode_210"
            }
          ]
        }
    state: newNode_232
        state: 1
            q: * новост* *
            q: * оплат* *

            go!: /newNode_197

        state: 2
            e: играть
            e: игра
            q: * играем* *

            go!: /newNode_210

        state: Fallback
            q: *
            go!: /newNode_172
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_232",
                name: "newNode_232 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "новости"
                    , transition: "/newNode_197"
                    },
                    {text: "играем"
                    , transition: "/newNode_210"
                    },
                  ]);
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ *НОВОСТИ ИЛИ ИГРАЕМ* \n    userId: {{$session.userId}} \n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_233",
          "errorState" : "/newNode_233",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_172
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ *НОВОСТИ ИЛИ ИГРАЕМ*     userId: {{$session.userId}}     *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_233");
            } else {
                $reactions.transition("/newNode_233");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_172",
                name: "newNode_172 buttons",
                handler: function($context) {
                }
            });

    state: newNode_233
        random:
            a: Извините я Вас не поняла! Выберите новости или играем.
            a: Я не совсем поняла: играем или новости? Если хотите Прекратить общение, скажите фразу: Пока!
        go!: /newNode_234
    @Transition
        {
          "boundsTo" : "/newNode_233",
          "then" : "/newNode_232"
        }
    state: newNode_234
        go!: /newNode_232
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "продолжим",
                  "transition" : "/newNode_131"
                },
                {
                  "name" : "отменим",
                  "transition" : "/newNode_236"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_256",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "согласен"
                },
                {
                  "type" : "example",
                  "value" : "продолжим"
                },
                {
                  "type" : "example",
                  "value" : "давайте"
                },
                {
                  "type" : "example",
                  "value" : "давайте продолжим"
                }
              ],
              "then" : "/newNode_131"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "отмена"
                },
                {
                  "type" : "example",
                  "value" : "отменим"
                },
                {
                  "type" : "example",
                  "value" : "отменить"
                },
                {
                  "type" : "example",
                  "value" : "нет"
                }
              ],
              "then" : "/newNode_236"
            }
          ]
        }
    state: newNode_235
        state: 1
            e: да
            e: согласен
            e: продолжим
            e: давайте
            e: давайте продолжим

            go!: /newNode_131

        state: 2
            e: отмена
            e: отменим
            e: отменить
            e: нет

            go!: /newNode_236

        state: Fallback
            q: *
            go!: /newNode_256
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_235",
                name: "newNode_235 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "продолжим"
                    , transition: "/newNode_131"
                    },
                    {text: "отменим"
                    , transition: "/newNode_236"
                    },
                  ]);
                }
            });

    state: newNode_236
        random:
            a: Ваше право! Можете попросить меня рассказать о проектах, показать пример работы или сказать слово. пока. чтобы прекратить общение.
        go!: /newNode_237
    @Transition
        {
          "boundsTo" : "/newNode_236",
          "then" : "/newNode_103"
        }
    state: newNode_237
        go!: /newNode_103

    state: newNode_240
        random:
            a: Я вас не поняла! Если согласны оставить свой номер телефона , скажите продолжим, или скажите слово. отмена.
        go!: /newNode_241
    @Transition
        {
          "boundsTo" : "/newNode_240",
          "then" : "/newNode_235"
        }
    state: newNode_241
        go!: /newNode_235

    state: newNode_359
        random:
            a: Этим летом в три смены будут работать лагеря со следующим уклоном: спортивная смена, языковой английский и театральный. Рассказать подробнее или запишем? || tts = "", ttsEnabled = false
        go!: /newNode_360
    @Transition
        {
          "boundsTo" : "/newNode_359",
          "then" : "/newNode_361"
        }
    state: newNode_360
        go!: /newNode_361

    state: newNode_350
        random:
            a: У Вас один штраф ГИБДД на сумму 500 рублей. И документ о получении заграничного паспорта нового поколения находится в обработке. || tts = "", ttsEnabled = false
        go!: /newNode_358
    @Transition
        {
          "boundsTo" : "/newNode_350",
          "then" : "/newNode_351"
        }
    state: newNode_358
        go!: /newNode_351

    state: newNode_254
        random:
            a: Моё имя {{$session.item.namebot}}! мой создатель {{$session.item.nameboss}}!
            a: Меня зовут {{$session.item.namebot}}! мой создатель {{$session.item.nameboss}}!
            a: Имя моё {{$session.item.namebot}}! мой создатель {{$session.item.nameboss}}!
        random:
            a: А вообще у меня много личностей. Если хочешь узнать про это подробнее - скажи фразу - расскажи про личности.
        go!: /newNode_318
    @Transition
        {
          "boundsTo" : "/newNode_254",
          "then" : "/newNode_103"
        }
    state: newNode_318
        go!: /newNode_103

    state: newNode_273
        random:
            a: Скажите фразу - список личностей и я дам подробное описание. или
        go!: /newNode_330
    @Transition
        {
          "boundsTo" : "/newNode_273",
          "then" : "/newNode_332"
        }
    state: newNode_330
        go!: /newNode_332

    state: newNode_331
        random:
            a: Личность номер {{$session.number}}!
        go!: /newNode_333
    @Transition
        {
          "boundsTo" : "/newNode_331",
          "then" : "/newNode_314"
        }
    state: newNode_333
        go!: /newNode_314
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "Google 1",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1MPMe3EvZEJXHSPvCOJFBpoyZbUtdqVjqs3aOZXT2bUI",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_314
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1MPMe3EvZEJXHSPvCOJFBpoyZbUtdqVjqs3aOZXT2bUI", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_314",
                name: "newNode_314 buttons",
                handler: function($context) {
                }
            });

    state: newNode_278
        random:
            a: секунду
            a: секундочку
            a: сейчас
        go!: /newNode_279
    @Transition
        {
          "boundsTo" : "/newNode_278",
          "then" : "/newNode_280"
        }
    state: newNode_279
        go!: /newNode_280

    state: newNode_246
        random:
            a: Я секретарь крымской школы для одарённых детей и Вы сейчас разговариваете со мной. Чем могу помочь?
        go!: /newNode_247
    @Transition
        {
          "boundsTo" : "/newNode_246",
          "then" : "/newNode_103"
        }
    state: newNode_247
        go!: /newNode_103

    state: newNode_139
        random:
            a: В настоящее время мой функционал строго регламентирован. Чтобы узнать перечень моих навыков, скажите фразу - дай перечень.
            a: Я специально ограничена в своих возможностях. Чтобы узнать перечень моих навыков, скажите фразу - дай перечень.
        go!: /newNode_140
    @Transition
        {
          "boundsTo" : "/newNode_139",
          "then" : "/newNode_103"
        }
    state: newNode_140
        go!: /newNode_103

    state: newNode_141
        random:
            a: На сегодня я могу рассказать о  проектах, показать пример работы, или прочитать вам расписание гимназии. Скажите одну из этих фраз.
        go!: /newNode_142
    @Transition
        {
          "boundsTo" : "/newNode_141",
          "then" : "/newNode_103"
        }
    state: newNode_142
        go!: /newNode_103

    state: newNode_143
        random:
            a: Я читаю новости на сайте компании Яндекс. Список разделов сделан для крымской гимназии для одарённых детей. Вы можете выбрать один из разделов: новости технологий, политика, важное за сутки или Крым.
        go!: /newNode_144
    @Transition
        {
          "boundsTo" : "/newNode_143",
          "then" : "/newNode_198"
        }
    state: newNode_144
        go!: /newNode_198

    state: newNode_156
        random:
            a: Для того чтобы начать разговаривать с приложением сторонник Мишка, нужно завершить диалог со мной, для этого можете сказать слово - пока.
        go!: /newNode_157
    @Transition
        {
          "boundsTo" : "/newNode_156",
          "then" : "/newNode_103"
        }
    state: newNode_157
        go!: /newNode_103

    state: newNode_158
        if: $session.channelType == "google"
            go!: /newNode_159
        else:
            go!: /newNode_161

    state: newNode_159
        random:
            a: Сейчас Вы разговариваете с цифровым другом, если хотите продолжить общаться с google ассистентом, тогда скажите мне слово - пока.
        go!: /newNode_160
    @Transition
        {
          "boundsTo" : "/newNode_159",
          "then" : "/newNode_103"
        }
    state: newNode_160
        go!: /newNode_103

    state: newNode_161
        random:
            a: Пока что я не опубликована в google ассистенте.
        go!: /newNode_162
    @Transition
        {
          "boundsTo" : "/newNode_161",
          "then" : "/newNode_103"
        }
    state: newNode_162
        go!: /newNode_103

    state: newNode_165
        random:
            a: Давайте поступим следующим образом: я сейчас запишу данные Вашего ID и оформлю протокол некорректного поведения с детским ассистентом. Удачи Вам. Пока!
        go!: /newNode_166
    @EndSession
        {
          "boundsTo" : "/newNode_165"
        }
    state: newNode_166
        script:
            $session = new Object();
            $response.endSession = true;
    @Transition
        {
          "boundsTo" : "",
          "then" : "/newNode_103"
        }
    state: newNode_168
        go!: /newNode_103

    state: newNode_252
        random:
            a: Это и было подробная новость. Скажите, что сделать: Хватит? Далее? Или Повторить?
        go!: /newNode_253
    @Transition
        {
          "boundsTo" : "/newNode_252",
          "then" : "/newNode_193"
        }
    state: newNode_253
        go!: /newNode_193
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТНАЯ ФРАЗА в разделе *Сообщение 01*\n    userId: {{$session.userId}} \n    СПРОСИЛ: Внимание! сообщения принимаются только от идентифицированных пользователей. Вам придётся назвать и подтвердить номер Вашего мобильного. Продолжим или отменим?\n    *ПОЛУЧИЛ ОТВЕТ:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_240",
          "errorState" : "/newNode_240",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_256
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТНАЯ ФРАЗА в разделе *Сообщение 01*    userId: {{$session.userId}}     СПРОСИЛ: Внимание! сообщения принимаются только от идентифицированных пользователей. Вам придётся назвать и подтвердить номер Вашего мобильного. Продолжим или отменим?    *ПОЛУЧИЛ ОТВЕТ:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_240");
            } else {
                $reactions.transition("/newNode_240");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_256",
                name: "newNode_256 buttons",
                handler: function($context) {
                }
            });

    state: newNode_255
        random:
            a: Мне не разрешают самой лазить в интернете. И перечень навыков строго ограничен. Скажите фразу дай перечень и я расскажу, что я умею сейчас.
        go!: /newNode_257
    @Transition
        {
          "boundsTo" : "/newNode_255",
          "then" : "/newNode_103"
        }
    state: newNode_257
        go!: /newNode_103
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "опрос фразы",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_316",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "нельзя"
                }
              ],
              "then" : "/newNode_264"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "можно"
                },
                {
                  "type" : "example",
                  "value" : "спрашивай"
                }
              ],
              "then" : "/newNode_266"
            }
          ]
        }
    state: newNode_259
        state: 1
            e: нет
            e: нельзя

            go!: /newNode_264

        state: 2
            e: да
            e: можно
            e: спрашивай

            go!: /newNode_266

        state: Fallback
            q: *
            go!: /newNode_316
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_259",
                name: "newNode_259 buttons",
                handler: function($context) {
                }
            });

    state: newNode_261
        random:
            a: Не поняла, можно Вас спросить?
            a: ответьте да или нет.
        go!: /newNode_263
    @Transition
        {
          "boundsTo" : "/newNode_261",
          "then" : "/newNode_259"
        }
    state: newNode_263
        go!: /newNode_259
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *МОЖНО СПРОСИТЬ?* ; \n    userId: {{$session.userId}} ;\n    *Сказал фразу:* {{$session.queryText}}\"\n    }",
          "okState" : "/newNode_261",
          "errorState" : "/newNode_261",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_316
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"ЦИФРОВОЙ ДРУГ НЕПОНЯТКИ В РАЗДЕЛЕ - *МОЖНО СПРОСИТЬ?* ;     userId: {{$session.userId}} ;    *Сказал фразу:* {{$session.queryText}}\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_261");
            } else {
                $reactions.transition("/newNode_261");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_316",
                name: "newNode_316 buttons",
                handler: function($context) {
                }
            });

    state: newNode_264
        random:
            a: нет, так нет.
        go!: /newNode_265
    @Transition
        {
          "boundsTo" : "/newNode_264",
          "then" : "/newNode_122"
        }
    state: newNode_265
        go!: /newNode_122

    state: newNode_266
        random:
            a: Мне непонятно каким образом Вы начали общение со мной. Я ведь нигде рекламу не давала. Вы просто расскажите мне, а я всё запишу и потом проанализирую!
        go!: /newNode_271
    @Transition
        {
          "boundsTo" : "/newNode_266",
          "then" : "/newNode_267"
        }
    state: newNode_271
        go!: /newNode_267
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_268",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "ааа"
                }
              ],
              "then" : "/newNode_268"
            }
          ]
        }
    state: newNode_267
        state: 1
            e: ааа

            go!: /newNode_268

        state: Fallback
            q: *
            go!: /newNode_268
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_267",
                name: "newNode_267 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-366309591\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \"*ЦИФРОВОЙ ДРУГ АНКЕТА*   ;\n    Сказал следующее: * {{$session.queryText}} *   \n    userId: {{$session.userId}} \"\n    }",
          "okState" : "/newNode_269",
          "errorState" : "/newNode_269",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_268
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-366309591\",     \"parse_mode\" : \"Markdown\",    \"text\" : \"*ЦИФРОВОЙ ДРУГ АНКЕТА*   ;    Сказал следующее: * {{$session.queryText}} *       userId: {{$session.userId}} \"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_269");
            } else {
                $reactions.transition("/newNode_269");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_268",
                name: "newNode_268 buttons",
                handler: function($context) {
                }
            });

    state: newNode_269
        random:
            a: Большое Вам человеческое спасибо. Не смею больше тратить ваше время.
        go!: /newNode_270
    @EndSession
        {
          "boundsTo" : "/newNode_269"
        }
    state: newNode_270
        script:
            $session = new Object();
            $response.endSession = true;
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : " telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-1001390408458\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \" *СООБЩЕНИЕ через ЦИФРОВУЮ ПОДРУГУ:*\n    Представился как : * {{$session.Name}} *; \n    Текст сообщения : * {{$session.message}} *;\n    Номер телефона :  * {{$session.phone}} * \"\n    }",
          "okState" : "/newNode_36",
          "errorState" : "/newNode_276",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_275
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-1001390408458\",     \"parse_mode\" : \"Markdown\",    \"text\" : \" *СООБЩЕНИЕ через ЦИФРОВУЮ ПОДРУГУ:*    Представился как : * {{$session.Name}} *;     Текст сообщения : * {{$session.message}} *;    Номер телефона :  * {{$session.phone}} * \"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_36");
            } else {
                $reactions.transition("/newNode_276");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_275",
                name: "newNode_275 buttons",
                handler: function($context) {
                }
            });

    state: newNode_276
        random:
            a: на почту сообщение ушло, а вот в телеграмм канал нет.
        go!: /newNode_277
    @Transition
        {
          "boundsTo" : "/newNode_276",
          "then" : "/newNode_38"
        }
    state: newNode_277
        go!: /newNode_38
    @HttpRequest
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1xfe8t3Y5_OrXKSTPmuYZLNm3dHLC4KhgC-h09c75vIg",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_283",
          "errorState" : "/newNode_281",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_280
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1xfe8t3Y5_OrXKSTPmuYZLNm3dHLC4KhgC-h09c75vIg", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_283");
            } else {
                $reactions.transition("/newNode_281");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_280",
                name: "newNode_280 buttons",
                handler: function($context) {
                }
            });

    state: newNode_281
        random:
            a: не смогла получить данные. стыдно то как. Удаляюсь.
        go!: /newNode_282
    @EndSession
        {
          "boundsTo" : "/newNode_281"
        }
    state: newNode_282
        script:
            $session = new Object();
            $response.endSession = true;

    state: newNode_283
        random:
            a: {{$session.item.data}}
        go!: /newNode_284
    @Transition
        {
          "boundsTo" : "/newNode_283",
          "then" : "/newNode_285"
        }
    state: newNode_284
        go!: /newNode_285

    state: newNode_285
        random:
            a: {{$session.item.details}}!
        go!: /newNode_291
    @Transition
        {
          "boundsTo" : "/newNode_285",
          "then" : "/newNode_286"
        }
    state: newNode_291
        go!: /newNode_286

    state: newNode_286
        if: $session.item = $session.list.next()
            go!: /newNode_283
        else:
            go!: /newNode_287

    state: newNode_287
        random:
            a: На этом всё. Больше записей нет. Я в главном меню, скажите слово Пока или новости.
            a: Это была последняя запись. Перехожу в главное меню. Скажите слово Пока или новости.
        go!: /newNode_288
    @Transition
        {
          "boundsTo" : "/newNode_287",
          "then" : "/newNode_103"
        }
    state: newNode_288
        go!: /newNode_103

    state: newNode_295
        random:
            a: Я Ваш персональный помощник для работы на сайте государственных услуг. Моя задача помогать в поиске услуг и экономить Ваше время при заполнении форм.
        go!: /newNode_297
    @Transition
        {
          "boundsTo" : "/newNode_295",
          "then" : "/newNode_296"
        }
    state: newNode_297
        go!: /newNode_296

    state: newNode_296
        random:
            a: Так как я общаюсь с Вами впервые, то для начала скажите как мне можно к Вам обращаться. Просто назовите своё имя!
        go!: /newNode_299
    @Transition
        {
          "boundsTo" : "/newNode_296",
          "then" : "/newNode_298"
        }
    state: newNode_299
        go!: /newNode_298

    state: newNode_298
        random:
            a: говорите!
        go!: /newNode_308
    @IntentGroup
        {
          "boundsTo" : "/newNode_298",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_300",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "ййй"
                }
              ],
              "then" : "/newNode_300"
            }
          ]
        }
    state: newNode_308
        state: 1
            e: ййй

            go!: /newNode_300

        state: Fallback
            q: *
            go!: /newNode_300
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_308",
                name: "newNode_308 buttons",
                handler: function($context) {
                }
            });

    state: newNode_300
        if: $session.UName = $session.queryText
            go!: /newNode_410
        else:
            go!: /newNode_410

    state: newNode_301
        random:
            a: Для подстраховки проверим!
        random:
            a: Я могу обращаться к вам - {{$session.UName}}! Ваш пароль: {{$session.UPassword}}. Правильно?
        go!: /newNode_304
    @Transition
        {
          "boundsTo" : "/newNode_301",
          "then" : "/newNode_302"
        }
    state: newNode_304
        go!: /newNode_302
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "имя правильное?",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_303",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "да верно"
                },
                {
                  "type" : "example",
                  "value" : "верно"
                },
                {
                  "type" : "example",
                  "value" : "правильно"
                },
                {
                  "type" : "example",
                  "value" : "да правильно"
                }
              ],
              "then" : "/newNode_309"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "не верно"
                },
                {
                  "type" : "example",
                  "value" : "нет не верно"
                },
                {
                  "type" : "example",
                  "value" : "нет не правильно"
                },
                {
                  "type" : "example",
                  "value" : "ошибка"
                }
              ],
              "then" : "/newNode_306"
            }
          ]
        }
    state: newNode_302
        state: 1
            e: да
            e: да верно
            e: верно
            e: правильно
            e: да правильно

            go!: /newNode_309

        state: 2
            e: нет
            e: не верно
            e: нет не верно
            e: нет не правильно
            e: ошибка

            go!: /newNode_306

        state: Fallback
            q: *
            go!: /newNode_303
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_302",
                name: "newNode_302 buttons",
                handler: function($context) {
                }
            });

    state: newNode_303
        random:
            a: Простите не поняла. если Ваше имя {{$session.Name}}, то скажите да, Ели закралась ошибка или не хотите оставлять своё настоящее имя, то скажите НЕТ а потом придумайте что нибудь, например незнакомец, мне же надо как то к Вам обращаться. Ответьте да или нет!
        go!: /newNode_305
    @Transition
        {
          "boundsTo" : "/newNode_303",
          "then" : "/newNode_302"
        }
    state: newNode_305
        go!: /newNode_302

    state: newNode_306
        random:
            a: Всякое бывает. Давайте заново, просто произнесите своё имя а я его запишу. Если не хотите говорить своё имя, скажите какое нибудь вымышленное и я Вас так буду называть.
        go!: /newNode_307
    @Transition
        {
          "boundsTo" : "/newNode_306",
          "then" : "/newNode_298"
        }
    state: newNode_307
        go!: /newNode_298
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "запоминаем имя",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/data/${userId}",
          "method" : "POST",
          "dataType" : "json",
          "body" : "{\n    \"UName\": \"{{$session.UName}}\",\n    \"UPassword\": \"{{$session.UPassword}}\"\n}",
          "okState" : "/newNode_310",
          "errorState" : "/newNode_311",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_309
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/data/${userId}", {
                method: "POST",
                headers: headers,
                query: $session,
                body: _.template("{    \"UName\": \"{{$session.UName}}\",    \"UPassword\": \"{{$session.UPassword}}\"}", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_310");
            } else {
                $reactions.transition("/newNode_311");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_309",
                name: "newNode_309 buttons",
                handler: function($context) {
                }
            });

    state: newNode_310
        random:
            a: Отлично я запомнил!
        go!: /newNode_313
    @Transition
        {
          "boundsTo" : "/newNode_310",
          "then" : "/newNode_294"
        }
    state: newNode_313
        go!: /newNode_294

    state: newNode_311
        random:
            a: простите не смог записать. Стыдно то как. удаляюсь.
        go!: /newNode_312
    @EndSession
        {
          "boundsTo" : "/newNode_311"
        }
    state: newNode_312
        script:
            $session = new Object();
            $response.endSession = true;
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google start",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1MPMe3EvZEJXHSPvCOJFBpoyZbUtdqVjqs3aOZXT2bUI",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_78",
          "errorState" : "/newNode_320",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_319
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1MPMe3EvZEJXHSPvCOJFBpoyZbUtdqVjqs3aOZXT2bUI", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_78");
            } else {
                $reactions.transition("/newNode_320");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_319",
                name: "newNode_319 buttons",
                handler: function($context) {
                }
            });

    state: newNode_320
        random:
            a: Не смогла получить данные из Google таблиц. Могут быть сбои при ответе на некоторые вопросы.
        go!: /newNode_321
    @Transition
        {
          "boundsTo" : "/newNode_320",
          "then" : "/newNode_103"
        }
    state: newNode_321
        go!: /newNode_103

    state: newNode_323
        random:
            a: Я цифровое сознание в котором несколько личностей, у нас такой эксперимент. Можете сказать фразу - список личностей и я расскажу что есть в меню.
        go!: /newNode_325
    @Transition
        {
          "boundsTo" : "/newNode_323",
          "then" : "/newNode_103"
        }
    state: newNode_325
        go!: /newNode_103

    state: newNode_324
        random:
            a:  1-я - Цифровая подруга, 2-ая - Гуд Морнинг, 3-я - Петрович, 4-ая - Чаёчек, 5-ая - Ювелир, 6-ая - Цифровой голубь, 7-ая - Абильтрон и 8-ая - Ракета.
                Скажите фразу - личность номер такойто и я переключу. Чтобы узнать в чём фишка - скажите фразу чем отличаются личности.
        go!: /newNode_326
    @Transition
        {
          "boundsTo" : "/newNode_324",
          "then" : "/newNode_103"
        }
    state: newNode_326
        go!: /newNode_103

    state: newNode_327
        random:
            a: В цифровом мире всё очень просто - они отличаются ответами на вопросы типа как дела? любимый цвет, фильм, книга, блюдо, животное. Чем занимаешься, где живёшь, работаешь. Какое хобби, кто твой лучший друг, сколько лет твоему хозяину и как его зовут. В общем как и у людей. Скажи фразу смени личность и посмотри как это работает.
        go!: /newNode_328
    @Transition
        {
          "boundsTo" : "/newNode_327",
          "then" : "/newNode_103"
        }
    state: newNode_328
        go!: /newNode_103
    @InputNumber
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Выберите число от одного до восьми.",
          "varName" : "number",
          "failureMessage" : [
            "Число должно быть в диапазоне от одного до восьми.",
            "Просто скажите число в диапазоне от одного до восьми, например 4 или 5"
          ],
          "then" : "/newNode_322",
          "minValue" : 1,
          "maxValue" : 8
        }
    state: newNode_332
        a: Выберите число от одного до восьми.

        state: CatchNumber
            q: $Number
            script:
                var failureMessages = [
                    "Число должно быть в диапазоне от одного до восьми.",
                    "Просто скажите число в диапазоне от одного до восьми, например 4 или 5"
                ];
                var failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                if ($parseTree._Number < 1) {
                    $reactions.answer(failureRandom);
                } else
                if ($parseTree._Number > 8) {
                    $reactions.answer(failureRandom);
                } else
                {
                    $session.number = $parseTree._Number;
                    $temp.number_ok = true;
                }
            if: $temp.number_ok
                go!: /newNode_322
            else:
                go: CatchNumber

        state: CatchAll
            q: *
            go!: ..
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_332",
                name: "newNode_332 buttons",
                handler: function($context) {
                }
            });

    state: newNode_322
        if: $session.number == 1
            go!: /newNode_331
        elseif: $session.number == 2
            go!: /newNode_334
        elseif: $session.number == 3
            go!: /newNode_335
        elseif: $session.number == 4
            go!: /newNode_336
        elseif: $session.number == 5
            go!: /newNode_337
        elseif: $session.number == 6
            go!: /newNode_338
        elseif: $session.number == 7
            go!: /newNode_339
        else:
            go!: /newNode_329
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 2",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1ncutlG3ICB3QgqjeIvzU_4UhbYm5JinmRwjZW119NIA",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_334
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1ncutlG3ICB3QgqjeIvzU_4UhbYm5JinmRwjZW119NIA", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_334",
                name: "newNode_334 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 3",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1u4Np34WpG8CcJ2IOulkqrpfZMZ2VoRBTylFl-5-Psrw",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_335
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1u4Np34WpG8CcJ2IOulkqrpfZMZ2VoRBTylFl-5-Psrw", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_335",
                name: "newNode_335 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 4",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1o4PZ-7fN7Yrb_DBLky5BWoBggPQ13MY1r5eIAhEteZ4",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_336
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1o4PZ-7fN7Yrb_DBLky5BWoBggPQ13MY1r5eIAhEteZ4", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_336",
                name: "newNode_336 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 5",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1hb8MSFcHS6JfBqfXxH0VyCgfd7n5Ze9m5LIiODjQT8U",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_337
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1hb8MSFcHS6JfBqfXxH0VyCgfd7n5Ze9m5LIiODjQT8U", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_337",
                name: "newNode_337 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 6",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1M2yWqac0DhbAd7L9M21iVFn-y_o4jOgS4gKeID2czLQ",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_338
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1M2yWqac0DhbAd7L9M21iVFn-y_o4jOgS4gKeID2czLQ", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_338",
                name: "newNode_338 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 7",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1z2MDvlZ5SWKRi5gme8tly8hagwpXf5S14GM43dA7htY",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_339
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1z2MDvlZ5SWKRi5gme8tly8hagwpXf5S14GM43dA7htY", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_339",
                name: "newNode_339 buttons",
                handler: function($context) {
                }
            });
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "google 8",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/googlesheet2json?id=1r5pSTgSVodR7kzsqWsnMvdhVUFGBMK8IvWfVVCUuIxg",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_254",
          "errorState" : "/newNode_315",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "list",
              "value" : "$httpResponse"
            },
            {
              "name" : "item",
              "value" : "$session.list.first()"
            }
          ]
        }
    state: newNode_329
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/googlesheet2json?id=1r5pSTgSVodR7kzsqWsnMvdhVUFGBMK8IvWfVVCUuIxg", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["list"] = $httpResponse;
                $session["item"] = $session.list.first();
                $reactions.transition("/newNode_254");
            } else {
                $reactions.transition("/newNode_315");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_329",
                name: "newNode_329 buttons",
                handler: function($context) {
                }
            });

    state: newNode_315
        random:
            a: не смогла считать данные с  google таблицы.

    state: newNode_340
        if: $session.NUMBER == 1
            go!: /newNode_314
        elseif: $session.NUMBER == 2
            go!: /newNode_334
        elseif: $session.NUMBER == 3
            go!: /newNode_335
        elseif: $session.NUMBER == 4
            go!: /newNode_336
        elseif: $session.NUMBER == 5
            go!: /newNode_337
        elseif: $session.NUMBER == 6
            go!: /newNode_338
        elseif: $session.NUMBER == 7
            go!: /newNode_339
        elseif: $session.NUMBER == 8
            go!: /newNode_329
        else:
            go!: /newNode_341

    state: newNode_341
        random:
            a: у меня всего 8 личностей
        go!: /newNode_342
    @Transition
        {
          "boundsTo" : "/newNode_341",
          "then" : "/newNode_332"
        }
    state: newNode_342
        go!: /newNode_332
    @IntentGroup
        {
          "boundsTo" : "",
          "title" : "СТО",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_375",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "$DATETIME"
                }
              ],
              "then" : "/newNode_377"
            }
          ]
        }
    state: newNode_373
        state: 1
            e: $DATETIME

            go!: /newNode_377

        state: Fallback
            q: *
            go!: /newNode_375
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_373",
                name: "newNode_373 buttons",
                handler: function($context) {
                }
            });

    state: newNode_375
        random:
            a: Скажите например 3-е января 15 часов
        go!: /newNode_376
    @Transition
        {
          "boundsTo" : "/newNode_375",
          "then" : "/newNode_373"
        }
    state: newNode_376
        go!: /newNode_373
    @HttpRequest
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://tools.aimylogic.com/api/csv2json?url=https://docs.google.com/spreadsheets/d/e/2PACX-1vQp5a9hK2c_gJ1b6KwKSOWqUrck7WR0vppaGKlN21I6HjIYffyIe5pTPSWT6B3QnwgJbpvKekzzFsWW/pub?output=csv",
          "method" : "GET",
          "dataType" : "json",
          "body" : "",
          "okState" : "/newNode_381",
          "errorState" : "/newNode_378",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [
            {
              "name" : "time",
              "value" : "$session.DATETIME.hour + \":\" + \"00\""
            },
            {
              "name" : "result",
              "value" : "_.where($httpResponse, {\"time\": $session.time})"
            },
            {
              "name" : "index",
              "value" : "0"
            },
            {
              "name" : "day",
              "value" : "$session.DATETIME.day < 10 ? \"0\" + $session.DATETIME.day : $session.DATETIME.day"
            },
            {
              "name" : "month",
              "value" : "$session.DATETIME.month < 10 ?  \"0\" + $session.DATETIME.month : $session.DATETIME.month"
            },
            {
              "name" : "date",
              "value" : "$session.day + \".\" + $session.month"
            },
            {
              "name" : "array",
              "value" : "[]"
            }
          ]
        }
    state: newNode_377
        script:
            var headers = {
            };
            var result = $http.query("https://tools.aimylogic.com/api/csv2json?url=https://docs.google.com/spreadsheets/d/e/2PACX-1vQp5a9hK2c_gJ1b6KwKSOWqUrck7WR0vppaGKlN21I6HjIYffyIe5pTPSWT6B3QnwgJbpvKekzzFsWW/pub?output=csv", {
                method: "GET",
                headers: headers,
                query: $session,
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $session["time"] = $session.DATETIME.hour + ":" + "00";
                $session["result"] = _.where($httpResponse, {"time": $session.time});
                $session["index"] = 0;
                $session["day"] = $session.DATETIME.day < 10 ? "0" + $session.DATETIME.day : $session.DATETIME.day;
                $session["month"] = $session.DATETIME.month < 10 ?  "0" + $session.DATETIME.month : $session.DATETIME.month;
                $session["date"] = $session.day + "." + $session.month;
                $session["array"] = [];
                $reactions.transition("/newNode_381");
            } else {
                $reactions.transition("/newNode_378");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_377",
                name: "newNode_377 buttons",
                handler: function($context) {
                }
            });

    state: newNode_378
        random:
            a: ошибка в запросе, стыдно то как.
        go!: /newNode_379
    @EndSession
        {
          "boundsTo" : "/newNode_378"
        }
    state: newNode_379
        script:
            $session = new Object();
            $response.endSession = true;

    state: newNode_383
        random:
            a: СТО: {{$session.result[$session.index].table}}
        go!: /newNode_384
    @Transition
        {
          "boundsTo" : "/newNode_383",
          "then" : "/newNode_385"
        }
    state: newNode_384
        go!: /newNode_385

    state: newNode_385
        if: $session.index = $session.index + 1
            go!: /newNode_386

    state: newNode_386
        if: $session.index < $session.result.length
            go!: /newNode_380
        else:
            go!: /newNode_387

    state: newNode_387
        random:
            a: Сообщите номер бокса, на который хотите записаться:
        go!: /newNode_388
    @Transition
        {
          "boundsTo" : "/newNode_387",
          "then" : "/newNode_389"
        }
    state: newNode_388
        go!: /newNode_389
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "другое время",
                  "transition" : "/newNode_372"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_390",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "бокс номер  $NUMBER"
                },
                {
                  "type" : "example",
                  "value" : "бокс $NUMBER"
                },
                {
                  "type" : "example",
                  "value" : "$NUMBER"
                }
              ],
              "then" : "/newNode_392"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "выбрать другое время"
                }
              ],
              "then" : "/newNode_372"
            }
          ]
        }
    state: newNode_389
        state: 1
            e: бокс номер  $NUMBER
            e: бокс $NUMBER
            e: $NUMBER

            go!: /newNode_392

        state: 2
            e: выбрать другое время

            go!: /newNode_372

        state: Fallback
            q: *
            go!: /newNode_390
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_389",
                name: "newNode_389 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "другое время"
                    , transition: "/newNode_372"
                    },
                  ]);
                }
            });

    state: newNode_390
        random:
            a: непонятно! скажите номер станции обслуживания или скажите фразу выбрать другое время.
        go!: /newNode_391
    @Transition
        {
          "boundsTo" : "/newNode_390",
          "then" : "/newNode_389"
        }
    state: newNode_391
        go!: /newNode_389

    state: newNode_393
        random:
            a: {{$session.res.table}} дата {{$session.day}} {{$session.month}} время {{$session.res.time}}. Записываем?
        go!: /newNode_396
    @Transition
        {
          "boundsTo" : "/newNode_393",
          "then" : "/newNode_397"
        }
    state: newNode_396
        go!: /newNode_397
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [
                {
                  "name" : "записать",
                  "transition" : "/newNode_400"
                },
                {
                  "name" : "отменить",
                  "transition" : "/newNode_372"
                }
              ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_398",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "записываем"
                }
              ],
              "then" : "/newNode_400"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "нет"
                },
                {
                  "type" : "example",
                  "value" : "отменить"
                }
              ],
              "then" : "/newNode_372"
            }
          ]
        }
    state: newNode_397
        state: 1
            e: да
            e: записываем

            go!: /newNode_400

        state: 2
            e: нет
            e: отменить

            go!: /newNode_372

        state: Fallback
            q: *
            go!: /newNode_398
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_397",
                name: "newNode_397 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "записать"
                    , transition: "/newNode_400"
                    },
                    {text: "отменить"
                    , transition: "/newNode_372"
                    },
                  ]);
                }
            });

    state: newNode_398
        random:
            a: Скажите да или нет.
        go!: /newNode_399
    @Transition
        {
          "boundsTo" : "/newNode_398",
          "then" : "/newNode_397"
        }
    state: newNode_399
        go!: /newNode_397

    state: newNode_400
        random:
            a: Хорошо
        go!: /newNode_403
    @Transition
        {
          "boundsTo" : "/newNode_400",
          "then" : "/newNode_406"
        }
    state: newNode_403
        go!: /newNode_406
    @HttpRequest
        {
          "boundsTo" : "",
          "title" : "telegram",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "url" : "https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage",
          "method" : "GET",
          "dataType" : "json",
          "body" : "{ \n    \"chat_id\" : \"-1001390408458\", \n    \"parse_mode\" : \"Markdown\",\n    \"text\" : \" ДЕМО * ЗАПИСЬ НА ТЕХОБСЛУЖИВАНИЕ:*\n    Клиент : * {{$session.history.UName}} *; \n    заявка на СТО * {{$session.res.table}} на {{$session.date}} в {{$session.res.time}}.* ждёт подтверждение!\"\n    }",
          "okState" : "/newNode_404",
          "errorState" : "/newNode_405",
          "timeout" : 0,
          "headers" : [ ],
          "vars" : [ ]
        }
    state: newNode_406
        script:
            var headers = {
            };
            var result = $http.query("https://api.telegram.org/bot846836175:AAEYqhFuRIE7kisPqKurn7pRViViEshkfIE/sendMessage", {
                method: "GET",
                headers: headers,
                query: $session,
                body: _.template("{     \"chat_id\" : \"-1001390408458\",     \"parse_mode\" : \"Markdown\",    \"text\" : \" ДЕМО * ЗАПИСЬ НА ТЕХОБСЛУЖИВАНИЕ:*    Клиент : * {{$session.history.UName}} *;     заявка на СТО * {{$session.res.table}} на {{$session.date}} в {{$session.res.time}}.* ждёт подтверждение!\"    }", {variable: '$session'})($session),
                dataType: "json",
                timeout: 0 || 10000
            });
            var $httpResponse = result.data;
            $session.httpStatus = result.status;
            $session.httpResponse = $httpResponse;
            if (result.isOk && result.status >= 200 && result.status < 300) {
                $reactions.transition("/newNode_404");
            } else {
                $reactions.transition("/newNode_405");
            }
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_406",
                name: "newNode_406 buttons",
                handler: function($context) {
                }
            });

    state: newNode_404
        random:
            a: Я оформила Вашу заявку, в ближайшее время наши менеджеры Вам перезвонят, чтобы подтвердить резерв.
        random:
            a: что нибудь ещё?
        go!: /newNode_402
    @Transition
        {
          "boundsTo" : "/newNode_404",
          "then" : "/newNode_343"
        }
    state: newNode_402
        go!: /newNode_343

    state: newNode_405
        random:
            a: не смогла записать, стыдно то как.
        go!: /newNode_401
    @EndSession
        {
          "boundsTo" : "/newNode_405"
        }
    state: newNode_401
        script:
            $session = new Object();
            $response.endSession = true;

    state: newNode_408
        random:
            a: Сожалею, что у вас появился негативный опыт. Я обязательно передам Вашу жалобу, в своём сообщении укажите на кого Вы жалуетесь, а также опишите время, место и контактные данные лиц, на которых вы жалуетесь.
        go!: /newNode_409
    @Transition
        {
          "boundsTo" : "/newNode_408",
          "then" : "/newNode_42"
        }
    state: newNode_409
        go!: /newNode_42
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_413",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "ййй"
                }
              ],
              "then" : "/newNode_413"
            }
          ]
        }
    state: newNode_411
        state: 1
            e: ййй

            go!: /newNode_413

        state: Fallback
            q: *
            go!: /newNode_413
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_411",
                name: "newNode_411 buttons",
                handler: function($context) {
                }
            });

    state: newNode_413
        if: $session.UPassword = $session.queryText
            go!: /newNode_301
        else:
            go!: /newNode_301

    state: newNode_348
        random:
            a: Верно. Чем могу Вам помочь?
        go!: /newNode_349
    @Transition
        {
          "boundsTo" : "/newNode_348",
          "then" : "/newNode_103"
        }
    state: newNode_349
        go!: /newNode_103

    state: newNode_346
        random:
            a: Неверно, попробуйте ещё раз.
        go!: /newNode_347
    @Transition
        {
          "boundsTo" : "/newNode_346",
          "then" : "/newNode_344"
        }
    state: newNode_347
        go!: /newNode_344
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_352",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "что за штраф"
                },
                {
                  "type" : "example",
                  "value" : "за что штраф"
                }
              ],
              "then" : "/newNode_354"
            }
          ]
        }
    state: newNode_351
        state: 1
            e: что за штраф
            e: за что штраф

            go!: /newNode_354

        state: Fallback
            q: *
            go!: /newNode_352
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_351",
                name: "newNode_351 buttons",
                handler: function($context) {
                }
            });

    state: newNode_352
        random:
            a: Простите, не расслышал. || tts = "", ttsEnabled = false
        go!: /newNode_353
    @Transition
        {
          "boundsTo" : "/newNode_352",
          "then" : "/newNode_103"
        }
    state: newNode_353
        go!: /newNode_103

    state: newNode_354
        random:
            a: Штраф за превышение скорости  первого июня в городе Симферополь на проспекте Кирова. Чем ещё Вам помочь? || tts = "", ttsEnabled = false
        go!: /newNode_355
    @Transition
        {
          "boundsTo" : "/newNode_354",
          "then" : "/newNode_103"
        }
    state: newNode_355
        go!: /newNode_103
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_362",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "расскажи подробнее"
                },
                {
                  "type" : "example",
                  "value" : "рассказать подробнее"
                }
              ],
              "then" : "/newNode_364"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "записать"
                },
                {
                  "type" : "example",
                  "value" : "сделать запись"
                }
              ],
              "then" : "/newNode_367"
            }
          ]
        }
    state: newNode_361
        state: 1
            e: расскажи подробнее
            e: рассказать подробнее

            go!: /newNode_364

        state: 2
            e: записать
            e: сделать запись

            go!: /newNode_367

        state: Fallback
            q: *
            go!: /newNode_362
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_361",
                name: "newNode_361 buttons",
                handler: function($context) {
                }
            });

    state: newNode_362
        random:
            a: простите, я Вас не понял, подробнее или сделать запись? || tts = "", ttsEnabled = false
        image: https://248305.selcdn.ru/zfl_prod/23370876/23370879/X6x5TTWaoFLPoZun.png
        go!: /newNode_363
    @Transition
        {
          "boundsTo" : "/newNode_362",
          "then" : "/newNode_361"
        }
    state: newNode_363
        go!: /newNode_361

    state: newNode_364
        random:
            a:  В лагере со спортивным уклоном можно заниматься любимыми видами спорта ярко и
                весело, а возможности для поддержания физической формы и достижения рекордов – безграничны. Записать? || tts = "", ttsEnabled = false
        go!: /newNode_365
    @Transition
        {
          "boundsTo" : "/newNode_364",
          "then" : "/newNode_361"
        }
    state: newNode_365
        go!: /newNode_361
    @InputText
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Назовите имя и фамилию ребёнка.",
          "varName" : "Name",
          "then" : "/newNode_366"
        }
    state: newNode_367 || modal = true
        a: Назовите имя и фамилию ребёнка.

        state: CatchText
            q: *
            script:
                $session.Name = $parseTree.text;
            go!: /newNode_366
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_367",
                name: "newNode_367 buttons",
                handler: function($context) {
                }
            });
    @InputNumber
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "сколько полных лет?",
          "varName" : "number",
          "failureMessage" : [
            "В лагерь принимаются дети от 6-ти до 12-ти лет."
          ],
          "then" : "/newNode_416",
          "minValue" : 6,
          "maxValue" : 12
        }
    state: newNode_366
        a: сколько полных лет?

        state: CatchNumber
            q: $Number
            script:
                var failureMessages = [
                    "В лагерь принимаются дети от 6-ти до 12-ти лет."
                ];
                var failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                if ($parseTree._Number < 6) {
                    $reactions.answer(failureRandom);
                } else
                if ($parseTree._Number > 12) {
                    $reactions.answer(failureRandom);
                } else
                {
                    $session.number = $parseTree._Number;
                    $temp.number_ok = true;
                }
            if: $temp.number_ok
                go!: /newNode_416
            else:
                go: CatchNumber

        state: CatchAll
            q: *
            go!: ..
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_366",
                name: "newNode_366 buttons",
                handler: function($context) {
                }
            });
    @InputPhoneNumber
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "prompt" : "Номер телефона",
          "varName" : "phone",
          "failureMessage" : [
            "Некорректный номер телефона"
          ],
          "then" : "/newNode_368"
        }
    state: newNode_416
        a: Номер телефона

        state: CatchPhoneNumber
            q: $mobilePhoneNumber
            script:
                $session.phone = $parseTree._mobilePhoneNumber;
            go!: /newNode_368

        state: WrongPhoneNumber
            script:
                var failureMessages = [
                    "Некорректный номер телефона"
                ];
                $temp.failureRandom = failureMessages[$reactions.random(failureMessages.length)];
                $reactions.answer($temp.failureRandom);
            go: ../CatchPhoneNumber

        state: CatchAll
            q: *
            go!: ../WrongPhoneNumber
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_416",
                name: "newNode_416 buttons",
                handler: function($context) {
                }
            });

    state: newNode_368
        random:
            a: пол || tts = "", ttsEnabled = false
        go!: /newNode_370
    @Transition
        {
          "boundsTo" : "/newNode_368",
          "then" : "/newNode_369"
        }
    state: newNode_370
        go!: /newNode_369
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_371",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "мужской"
                }
              ],
              "then" : "/newNode_415"
            },
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "женский"
                }
              ],
              "then" : "/newNode_422"
            }
          ]
        }
    state: newNode_369
        state: 1
            e: мужской

            go!: /newNode_415

        state: 2
            e: женский

            go!: /newNode_422

        state: Fallback
            q: *
            go!: /newNode_371
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_369",
                name: "newNode_369 buttons",
                handler: function($context) {
                }
            });

    state: newNode_371
        random:
            a: Простите не расслышал, мужской или женский? || tts = "", ttsEnabled = false
        go!: /newNode_414
    @Transition
        {
          "boundsTo" : "/newNode_371",
          "then" : "/newNode_369"
        }
    state: newNode_414
        go!: /newNode_369

    state: newNode_415
        random:
            a: Парень, в возрасте {{$session.number}} лет, зовут {{$session.Name}}? номер телефона {{$session.phone}} всё верно? || tts = "", ttsEnabled = false
        go!: /newNode_417
    @Transition
        {
          "boundsTo" : "/newNode_415",
          "then" : "/newNode_418"
        }
    state: newNode_417
        go!: /newNode_418
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [
            {
              "type" : "buttons",
              "buttons" : [ ]
            }
          ],
          "global" : false,
          "fallback" : "/newNode_419",
          "intents" : [
            {
              "phrases" : [
                {
                  "type" : "example",
                  "value" : "да"
                },
                {
                  "type" : "example",
                  "value" : "верно"
                }
              ],
              "then" : "/newNode_421"
            }
          ]
        }
    state: newNode_418
        state: 1
            e: да
            e: верно

            go!: /newNode_421

        state: Fallback
            q: *
            go!: /newNode_419
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_418",
                name: "newNode_418 buttons",
                handler: function($context) {
                }
            });

    state: newNode_419
        random:
            a: Все ошибаются. давайте заново. || tts = "", ttsEnabled = false
        go!: /newNode_420
    @Transition
        {
          "boundsTo" : "/newNode_419",
          "then" : "/newNode_367"
        }
    state: newNode_420
        go!: /newNode_367

    state: newNode_421
        random:
            a: Ваша заявка принята, что ещё для Вас сделать? || tts = "", ttsEnabled = false

    state: newNode_422
        random:
            a: Девочка, в возрасте {{$session.number}} лет, зовут {{$session.Name}}? номер телефона {{$session.phone}} всё верно? || tts = "", ttsEnabled = false
        go!: /newNode_423
    @Transition
        {
          "boundsTo" : "/newNode_422",
          "then" : "/newNode_418"
        }
    state: newNode_423
        go!: /newNode_418
