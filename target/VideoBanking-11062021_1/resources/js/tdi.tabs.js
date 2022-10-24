(
        function ($) {

            var ctTab = Object();
            var ctKey = Object();
            var tabList = Object();

            $.fn.dynatabs = function (options) {

                var tabs = $(this.selector);

                var settings = $.extend({
                    tabBodyID: 'tabbody',
                    defaultTab: 0, //default is 0 - first tab
                    deactiveClass: 'unselected',
                    activeClass: 'selected',
                    showCloseBtn: false, //shows the close button on the tabs
                    closeableClass: 'closeable',
                    tabLoaderClass: 'tabLoader',
                    confirmDelete: false,
                    confirmMessage: 'Delete Tab?',
                    switchToNewTab: true,
                    debug: false

                }, options);

                $.fn.debug = function (message) {
                    if (settings.debug)
                    {
                        if ($.browser.webkit || $.browser.mozilla)
                        {
                            console.log(message);
                        }
                        else
                        {
                            alert('You have debug enabled in settings. It is only supported in Firefox and Chrome now.');
                        }
                    }
                };

                /**
                 * Function to show a tab
                 */
                $.fn.showTab = function (event) {

                    var ahref = event.data.ahref;
                    var tab = event.data.tab;
                    if (ahref != null)
                    {
                        $.fn.activateTab($(ahref).attr('href'), ahref, tab);
                    }
                    else
                    {
                        $.fn.debug('unable to show a null tab');
                    }
                };

                $.fn.closeTab = function (event) {

                    if (event.data.key != null && event.data.tab != null)
                    {
                        $.fn.debug('deleting tab');
                        var canDelete = false;
                        var ahref = null;
                        //check if the tab can be deleted
                        if (settings.confirmDelete)
                        {
                            if (confirm(settings.confirmMessage))
                            {
                                canDelete = true;
                            }
                        }
                        else
                        {
                            canDelete = true;
                        }

                        //delete the tab
                        if (canDelete)
                        {
                            //find the ahref
                            ahref = tabs.find("a[href='" + event.data.key + "']");
                            if (ahref != null && ahref.length == 1)
                            {
                                //delete it
                                $(ahref).parent().remove();
                                $(event.data.key).remove();
                                //activate the next tab (if any)
                                if (tabs.find("a").length > 0)
                                {
                                    var tmp = tabs.find("a")[0];
                                    $.fn.activateTab($(tmp).attr('href'), tmp, event.data.tab);
                                }
                            }
                            else
                            {
                                $.fn.debug('Too many A Hrefs found with id ' + event.data.key);
                            }
                        }
                    }

                    return false;
                };

                $.fn.activateTab = function (key, ahref, tab) {
                    if (key != null && ahref != null && tab != null)
                    {
                        $.fn.debug(tab);
                        $.fn.debug(key);
                        $.fn.hideTab(ctKey[$(tab).attr('id')], ctTab[$(tab).attr('id')]);
                        $(ahref).parent().addClass(settings.activeClass);
                        $(key).show();
                        ctKey[$(tab).attr('id')] = key;
                        ctTab[$(tab).attr('id')] = ahref;
                    }
                };

                $.fn.hideTab = function (key, ahref) {

                    if (key != null && ahref != null)
                    {
                        $(ahref).parent().removeClass(settings.activeClass);
                        //$(ahref).parent().addClass(settings.deactiveClass);
                        $(key).hide();
                    }

                };

                /**
                 * Bind the on-click of each tab to showtab function
                 */
                $.fn.bindTabs = function () {
                    $.each(tabs.find("li > a"), function (idx, a) {
                        //bind click function of the tab header
                        $(a).bind('click', {ahref: a, tab: tabs}, $.fn.showTab);
                        //show the close button if enabled in settings
                        if (settings.showCloseBtn && $(a).find("span").length == 0)
                        {
                            $.fn.addCloseBtn(a);
                        }
                    });
                };

                $.fn.addCloseBtn = function (ahref) {

                    if (ahref != null)
                    {
                        this.debug('adding close button');
                        var key = $(ahref).attr('href');
                        if (key.length > 0)
                        {
                            $(ahref).append('<span class="' + settings.closeableClass + '"></span>');
                            $(ahref).find("span").bind('click', {key: key, tab: tabs}, $.fn.closeTab);
                        }
                    }

                };

                $.fn.addTabLoader = function (ahref) {

                    if (ahref != null)
                    {
                        this.debug('adding tab loader button');
                        var key = $(ahref).attr('href');
                        if (key.length > 0)
                        {
                            $(ahref).append('<span class="' + settings.tabLoaderClass + '"></span>');
                            $(ahref).find("span").bind('click', {key: key}, $.fn.closeTab);
                        }
                    }

                };

                $.fn.addNewTab = function (defaults, tabBody) {

                    settings.tabBodyID = tabBody;
                    tabs = $("#" + defaults.tabID);
                    $.fn.debug('Tab ID : ' + defaults.tabID);
                    if (defaults.type === 'html')
                    {
                        if (defaults.html !== null && defaults.html.length > 0)
                        {
                            //create the title tag
                            var a = $("<a />");
                            //create the li tag
                            var li = $("<li />");
                            //create data div
                            var div = $("<div />");
                            var sp = $("<span/>");
                            var len = tabs.find("li").length - 1;
                            $(sp).attr('id', '_' + settings.tabBodyID + 'span' + len);
                            $(sp).css("width", "20px");
                            $(sp).css("border-radius", "13px");
                            $(sp).css("text-align", "center");
                            $(sp).css("margin-left", "2px");
                            $(sp).css("padding", "2px");
                            $(sp).css("display", "none");


                            $(a).attr('href', '#' + settings.tabBodyID + len);
                            $(a).attr('id', '_' + settings.tabBodyID + len);
                            $(a).text(defaults.tabTitle);
                            $(a).click(function () {
                                $(a).removeClass("unselected").addClass("selected");
                                $('#lieveryone').removeClass("selected");
                                $(a).removeAttr('style');
                                $(sp).css("display", "none");
                                resetMsgCounter(len);
                                var x = document.getElementsByName("user");
                                var i;
                                for (i = 0; i < x.length; i++) {
                                    if (x[i].type === "radio") {
                                        if (x[i].id === defaults.tabTitle) {
                                            x[i].checked = true;
                                            var windowHeight = $(window).height();
                                            var videoheight = $('#vidyoConnector').css("height")
                                            var ratio = parseInt(videoheight) / parseInt(windowHeight)
                                            var verth = parseInt(ratio.toString().substring(2, 4));
                                            index=len;
                                            if (verth < 10)
                                            {
                                                verth = parseInt(verth) * 10;
                                            }
//                                            if (verth > 48) {
                                             if (document.getElementById('collapse1').style.display=='none') {    

                                                $(".nav-toggle").trigger("click");

                                            }
                                            else {
                                                var elms = document.getElementById('participant' + len).getElementsByTagName("div");
                                                if (elms.length > 0) {
                                                    var chatelement = elms[elms.length - 1];
                                                    chatelement.scrollIntoView();
                                                }
                                            }

                                        }
                                    }
                                }
                                setCurrentTab(defaults.tabTitle);
                            });
                            $(li).append(a);
                            $(a).append(sp);
                            $(div).attr('id', '' + settings.tabBodyID + len);
                            $(div).html(defaults.html);
                            //append to tab list
                            tabs.append(li);
                            $(div).addClass(settings.deactiveClass);
                            $('#' + settings.tabBodyID).append(div);

                            //bind all click functions to tab headers

                            $.fn.bindTabs();

                        }
                        else
                        {
                            $.fn.debug('No HTML content found for the new tab. Skipping new tab creation');
                        }
                    }

                };




                $.fn.initTabs = function () {

                    //hide all tabs other than the default tab index
                    var ct = 0;
                    $.fn.debug('Tab Body ID -->' + settings.tabBodyID);
                    $.each($("#" + settings.tabBodyID + " > div"), function (idx, div) {
                        if (ct != settings.defaultTab)
                        {
                            $.fn.debug('Hiding -- ' + div.outerHTML);
                            $(div).hide();
                            ct = ct + 1;
                        }
                        else
                        {
                            $.fn.debug('Showing -- ' + div.outerHTML);
                            ct = ct + 1;
                        }
                    });

                    //add the selected class to the title also
                    $.fn.debug(tabs);
                    $.fn.debug('Tab Lengths --> ' + tabs.find("li").length);
                    if (settings.defaultTab < tabs.find("li").length)
                    {
                        this.debug('setting active tab --> Index ' + settings.defaultTab);
                        $(tabs.find("li")[settings.defaultTab]).removeClass(settings.deactiveClass);
                        $(tabs.find("li")[settings.defaultTab]).addClass(settings.activeClass);
                        ctTab[tabs.attr('id')] = $(tabs.find("li")[0]).find("a");
                        ctKey[tabs.attr('id')] = $(tabs.find("li")[0]).find("a").attr('href');
                    }
                    else
                    {
                        $.fn.debug('Index ' + settings.defaultTab + ' does not map to li');
                    }

                    //add close buttons as neccessary to all tabs and bind clicks
                    this.bindTabs();
                    //add tabs to the list
                    tabList[tabs.attr('id')] = settings.tabBodyID;
                };

                this.initTabs();
            };

            $.addDynaTab = function (options) {

                var settings = $.extend({
                    type: 'html', //html or ajax or div
                    url: '', //mandatory for ajax requests
                    html: '', // mandatory for html content
                    divID: '', //mandatory for div method
                    method: 'post', //get or post for ajax urls
                    dtype: 'html', //json/html/text for ajax urls
                    params: {},
                    tabID: '',
                    tabTitle: 'New Tab'
                }, options);
                if (settings.tabID.length > 0)
                {

                    $.fn.addNewTab(settings, tabList[settings.tabID]);
                }
                else
                {
                    $.fn.debug('Please enter the tab id parameter');
                }

            };




            $.fn.removeDynaTab = function (options) {

            };

        }(jQuery)
        );