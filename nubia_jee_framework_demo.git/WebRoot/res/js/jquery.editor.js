;(function($){
    $.fn.selection = function(){
        var c = this[0].contentWindow,s=c.document.selection;                                                      
        return (s)?s.createRange().htmlText:$('<p/>').append($(c.getSelection().getRangeAt(0).cloneContents())).html();
    };

    $.fn.editor = function( ops ){
        var ops = $.extend({
            html : '<html><head><style>body{margin:4px;font:12px/18px Tahoma,Helvetica,Arial,sans-serif;cursor:text;} fieldset{border:1px solid #bbb;font:13px/1.5 Courier New;margin:4px;overflow-x:hidden;padding:4px;}</style></head><body>INIT_VAL</body></html>',
            ctrls : {}
        }, ops);

        ops.ctrls = $.extend(true, ops.ctrls, Rte.TOOLBAR);
        
        return this.each(function(){
            Rte(this, ops);
        });
    };
    
    $.fn.resize=function(ops) {
        var flag=false,self=this,vs=this.find('iframe'),bind='mousemove.resize';
        function pos(x,y){
            if (flag) {
                var o=self.offset(),x=x-o.left,y=y-o.top;
                self.height(y);
                vs.height(y-48);
            }
        };
        $(ops,this).mousedown(function(e){
            flag = true;
            $(document).bind(bind,function(e){pos(e.pageX,e.pageY);});
        }).mouseup(function(e){
            pos(e.pageX,e.pageY);
            flag = false;
            $(document).unbind(bind);
        });
        vs.bind('load',function(){
            $(this).contents().mousemove(function(e){
                if(e.pageX>0&&e.pageY>0) {
                    var o=vs.offset();
                    pos(o.left+e.pageX,o.top+e.pageY);
                }
            });
        });
        return self;
    };
    
    function Rte( el, ops ){
        return this instanceof Rte ? this.init(el, ops): new Rte(el, ops);
    }
        
    $.extend(Rte.prototype,{
        origin : null,
        ops  : {},
                
        init : function( el, ops ){
            this.origin = el;
            this.ops = ops || {};
            this.initVal = $(el).val();
            var self=this; 
            
            this.editor = $('<iframe id="'+$(this.origin).attr('id')+'_editor" marginWidth="0" marginHeight="0" frameborder="0"></iframe>')
            .height(el.clientHeight || el.rows * 22);
                       
            this.toolbar = $('<div class="toolbar"></div>');
            this.build();
            
            $(el).hide().before($('<div class="editor"></div>').append(this.toolbar)
              .append($('<div class="editarea"></div>').css({ clear : 'both' }).append(this.editor))
              .append($('<div class="msg"><div class="tip"></div></div>').append('<div class="resize"></div>')).resize('.resize'));
              
            this.create();
        },
        
        create : function(){
            var self = this;

            this.doc = $(this.editor)[0].contentWindow.document;
            this.doc.designMode = 'on';
            this.doc.open();
            this.doc.write(this.ops.html.replace(/INIT_VAL/, this.initVal));
            this.doc.close();
            this.doc.body.contentEditable = 'true';
                        
            $(this.doc).click(function(e){
                self.remove();
                self.check(e.target);
            });

            $(this.doc).keydown(function(e){
                if ( $.browser.msie && e.keyCode == 13 ){
                    var rng = $(self.editor)[0].contentWindow.document.selection.createRange();
                        rng.pasteHTML('<br />');
                        rng.collapse(false);
                        rng.select();
                    return false;
                }
            });
            
            $('form').submit(function() { self.save(); }).bind('reset', function(){
                  self.content( self.initVal );
                  self.save();
            });
            
            $(this.doc).keydown(function() { self.save(); })
            .keyup(function() { self.save(); })
            .mousedown(function() { self.save(); });
        },
                
        content : function(c){
            var b=$($(this.editor)[0].contentWindow.document ).find('body');
            return (c)?b.html(c):b.html();
        },

        save : function(){
            $(this.origin).val(this.content());
        },

        compose : function(name, args, fn ){
            var self = this;
            $('<a href="javascript:;" />').addClass(name).click(function(ev) {
                self.editor[0].contentWindow.focus();
                fn?fn.apply(self,[ev]):self.doc.execCommand(name, false, args);
            }).appendTo(this.toolbar);
        },

        build : function(){
            for ( var name in this.ops.ctrls ){
                var ctrl = this.ops.ctrls[name];
                if ( ctrl.show ){
                    this.compose(name, ctrl.arguments || [],ctrl.exec);
                }
            }
        },
        check : function(el){
            var cs='source,fontname,fontsize,undo,redo,unlink,page,table,forecolor,backcolor,code,image,media,smile,fullscreen,preview,removeformat,word';
            for ( var name in this.ops.ctrls ){
                if(cs.indexOf(name)>-1)continue;
                var ctrl = this.ops.ctrls[name],bt=$('.' + name,this.toolbar).removeClass('active');
                if (ctrl.tags){
                    var elm = el;
                    do {
                        if ( elm.nodeType != 1)
                            break;
                        if ( $.inArray(elm.tagName.toLowerCase(), ctrl.tags) != -1 )
                            bt.addClass('active');
                    } while ( elm = elm.parentNode );
                }
                if (ctrl.css){
                    var elm = $(el);
                    do {
                        if (elm[0].nodeType !=1)
                            break;
                        for ( var css in ctrl.css)
                            if ( elm.css(css).toString().toLowerCase() == ctrl.css[css] )
                                bt.addClass('active');
                    } while ( elm = elm.parent() );
                }
            }
        },
        pop:function(ev,isMenu,_o,_w,_h){
            this.remove();
            var obj=ev.target,h=obj.offsetHeight,x = obj.offsetLeft,y=obj.offsetTop;
            while((obj = obj.offsetParent) != null){
                x += obj.offsetLeft;
                y += obj.offsetTop;
            }
            return $('<div class="editor_pop"></div>')
            .attr('id',$(this.origin).attr('id')+'_pop')
            .css({left:_o+x+'px',top:(y+h+2)+'px'}).width(_w).height(_h)
            .addClass('editor_pop_'+(isMenu?'menu':'text'))
            .appendTo(document.body);
        },
        remove : function(r){
            $('#'+$(this.origin).attr('id')+'_pop').remove();
        },
        insert :function(t){
            var s=$('<p/>').append($(t)).html();
            this.editor[0].contentWindow.focus();                       
            if($.browser.msie){
                this.doc.selection.createRange().pasteHTML(s);
            }else{
                this.doc.execCommand('insertHTML',false,s);
            }
            this.save();
            this.remove();
        },
        cmd : function(name,arg){
            this.editor[0].contentWindow.focus();
            this.doc.execCommand(name,false,arg);
            this.remove();
        },
        color : function(name,ev){        
            var self=this,fs=this.colors,ep=this.pop(ev,false,0,100,114).append('<div class="bg"/><div class="rgb"/><div style="clear:both"/>');
            for(i in fs){
                ep.append($('<a href="javascript:;" class="color"/>').attr('rel','#'+fs[i]).text(' ').css({backgroundColor:'#'+fs[i]}).click(function(){
                    self.cmd(name,$(this).attr('rel'));
                }).hover(function(){
                    var t=$(this),v=t.attr('rel'),d=t.parent().find('div');
                    d.slice(0,1).css({backgroundColor:v});
                    d.slice(1,2).text(v);
                }));
            }
        },        
        fontnames: ['宋体','黑体','新宋体','Arial','Arial Black','Times New Roman','Courier New','Verdana'],
        fontsizes: [8,10,12,14,18,24],
        codes    : ['Assembly','C#','C/C++','CSS','HTML','Java','JavaScript','Delphi(Pascal)','Perl','PHP','Python','SQL','VB','VB.NET','VBScript','XML'],
        colors   : ['000000','330000','663300','663333','333300','003300','003333','000066','330099','330033','333333','660000','993300','996633','666600','006600','336666','000099','333399','663366','666666','990000','CC6600','CC9933','999900','009900','339999','3333FF','6600CC','993399','999999','CC0000','FF6600','FFCC33','FFCC00','33CC00','00CCCC','3366FF','6633FF','CC33CC','C0C0C0','FF0000','FF9900','FFCC66','FFFF00','33FF33','66CCCC','33CCFF','6666CC','CC66CC','CCCCCC','FF6666','FF9966','FFFF66','FFFF33','66FF99','33FFFF','66FFFF','9999FF','FF99FF','FFFFFF','FFCCCC','FFCC99','FFFF99','FFFFCC','99FF99','99FFFF','CCFFFF','CCCCFF','FFCCFF']
    });
       
    $.extend(Rte, {
        TOOLBAR : {
            source          : {
                show : true,
                exec    : function(){
                    var a=$('.toolbar > a'),b=$($(this.editor)[0].contentWindow.document).find('body'),c=a.slice(0,1),d=a.slice(1);
                    if (c.hasClass('active')){
                        b.html(b.text());c.removeClass('active');d.show();
                    }else{
                        b.text(b.html());c.addClass('active');d.hide();
                    }
                }
            },
            fontname        : {show : true,exec    : function(ev){
                var ep=this.pop(ev,true,0,112,184),self=this,fs=self.fontnames;
                for(i in fs){
                    ep.append($('<a href="javascript:;" class="menu"/>').attr('rel',fs[i]).text(fs[i]).css({fontFamily:fs[i]}).click(function(){
                        self.cmd('fontname',$(this).attr('rel'));
                    }));
                }
            }},
            fontsize        : {show : true,exec    : function(ev){
                var ep=this.pop(ev,true,0,112,184),self=this,fs=self.fontsizes;
                for(i in fs){
                    ep.append($('<a href="javascript:;" class="menu"/>').attr('rel',i).text(fs[i]+'pt').css({fontSize:fs[i]+'pt'}).click(function(){
                        self.cmd('fontsize',$(this).attr('rel'));
                    }));
                }
            }},
            bold            : {show : true, tags : ['b', 'strong'], css : { fontWeight : 'bold' } },
            italic          : {show : true, tags : ['i', 'em'], css : { fontStyle : 'italic' } },
            underline       : {show : true, tags : ['u'], css : { textDecoration : 'underline' } },
            strikethrough   : {show : true, tags : ['s', 'strike'], css : { textDecoration : 'line-through' } },
            justifyleft     : {show : true, css : { textAlign : 'left' } },
            justifycenter   : {show : true, tags : ['center'], css : { textAlign : 'center' } },
            justifyright    : {show : true, css : { textAlign : 'right' } },
            //justifyfull     : {show : true, css : { textAlign : 'justify' } },
            undo            : {show : true },
            redo            : {show : true },
           insertorderedlist: {show : true, tags : ['ol'] },
         insertunorderedlist: {show : true, tags : ['ul'] },
            indent          : {show : true },
            outdent         : {show : true },
            link            : {
                show : true,
                exec    : function(){
                    var selection = $(this.editor).selection();
                    if ( selection.length > 0 ){
                        if ( $.browser.msie )
                            this.doc.execCommand('createLink', true, []);
                        else{
                            var url = prompt('URL', 'http://');
                            if ( url && url.length > 0 ){
                                this.doc.execCommand('unlink', false, []);
                                this.doc.execCommand('createLink', false,url);
                            }
                        }
                    }else
                        alert("none");
                },
                tags : ['a']
            },
            unlink          : {show : true },
        inserthorizontalrule: {show : true, tags : ['hr'] },
            page            : {show : true, exec:function(ev){
                alert('Developing...');
            }},
            table           : {show : true,exec : function(ev){
                var ep=this.pop(ev,false,0,146,160).removeClass('editor_pop_text'),self=this;
                var tbl=$('<table/>');
                for(var i=0;i<10;i++){
                    var tr=$('<tr/>');
                    for(var j=0;j<10;j++){
                        $('<td/>').append($('<a href="javascript:;" id="a-'+i+'-'+j+'"/>').hover(function(){
                            var td=$(this).attr('id').replace('a-','').split('-');
                            var row=parseInt(td[0])+1,col=parseInt(td[1])+1;
                            $(this).parent().parent().parent().find('td:last').text('Rows: '+row+' * Cols: '+col);
                            
                            for(var m=0;m<10;m++)
                                for(var n=0;n<10;n++)
                                    $('#a-'+m+'-'+n).parent()[0].style.backgroundColor=(m<row&&n<col)?'#EEF3F7':'#FFFFFF';
                                    
                        }).click(function(e){
                            var td=$(this).attr('id').replace('a-','').split('-');
                            var row=parseInt(td[0])+1,col=parseInt(td[1])+1;
                                                        
                            var tb = document.createElement('table');
                            tb.cellPadding = 0;
                            tb.cellSpacing = 0;
                            tb.border = 1;
                            tb.style.width = '200px';
                            tb.style.height = '200px';
                            for (var i = 0; i < row; i++) {
                                var tr = tb.insertRow(i);
                                for (var j = 0; j < col; j++) {
                                    tr.insertCell(j).innerHTML =' ';
                                }
                            }
                            self.insert(tb);
                        })).appendTo(tr);
                    }
                    tr.appendTo(tbl);
                }
                tbl.append($('<tr><td colspan="10" class="tip">Rows: 0 * Cols: 0</td></tr>')).appendTo(ep);
            }},
            code            : {
                show : true,
                exec    : function(ev){
                    var self=this,fs=this.codes,ep=this.pop(ev,false,0,182,176);
                    for(i in fs){
                        ep.append($('<a href="javascript:;" class="lang"/>').attr('rel',fs[i]).text(fs[i]).click(function(){
                            var selection = $(self.editor).selection();
                            self.insert('<br/><fieldset><legend> '+$(this).attr('rel')+' </legend>'+(selection.length > 0 ? selection : 'code')+'</fieldset><br/>');
                        }));
                    }
                }
            },
            word            : {show : true,exec : function(ev){
                var self=this,ep=this.pop(ev,false,-160,300,210).append('<textarea style="width:297px;height:180px;overflow:auto;"></textarea><input type="button" value="Ok"/>');
            }},
            forecolor       : {show : true,exec : function(ev){
                this.color('forecolor',ev);                
            }},
            backcolor       : {show : true,exec : function(ev){
                this.color($.browser.msie?'backcolor':'hilitecolor',ev);
            }},
            image           : {show : true,exec :function(ev){
                var self=this,id=$(this.origin).attr('id'),ep=this.pop(ev,false,-180,240,50).append($('<iframe style="width:240px;height:50px;" id="jeditor_image" marginWidth="0" marginHeight="0" frameborder="0"  src="res/js/image.html?id='+id+'"></iframe>'));
                $('#jeditor_image').attr('src','res/js/image.html?id='+id);
            }},
            media           : {show : true ,exec :function(ev){
                var self=this,ep=this.pop(ev,false,-180,240,180).append($('<div>Developing...</div>'));
            }},
            smile           : {show : true ,exec :function(ev){
                var self=this,ep=this.pop(ev,false,-190,240,180).append($('<div>Developing...</div>'));
            }},
            fullscreen      : {show : false },
            removeformat    : {
                show : true,
                exec    : function(){
                    this.doc.execCommand('removeFormat', false, []);
                    this.doc.execCommand('unlink', false, []);
                    
                    var s = this.content().replace( new RegExp( '<p \\/>', 'gi' ), '<p>&nbsp;</p>' );
                    s = s.replace( new RegExp( '<p>\\s*<\\/p>', 'gi' ), '<p>&nbsp;</p>' );
                    s = s.replace( new RegExp( '<br>\\s*<\\/br>', 'gi' ), '<br />' );
                    s = s.replace( new RegExp( '<(h[1-6]|p|div|address|pre|form|table|li|ol|ul|td|b|font|em|strong|i|strike|u|span|a|ul|ol|li|blockquote)([a-z]*)([^\\\\|>]*)\\/>', 'gi' ), '<$1$2$3></$1$2>' );
                    s = s.replace( new RegExp( '\\s+></','gi' ), '></' );
                    s = s.replace( new RegExp( '<(img|br|hr)([^>]*)><\\/(img|br|hr)>', 'gi' ), '<$1$2 />' );
                    if ( $.browser.msie ) {
                        s = s.replace( new RegExp( '<p><hr \\/><\\/p>', 'gi' ), "<hr>" );
                        s = s.replace( /<!(\s*)\/>/g, '' );
                    };
                    this.content(s);
                }
            },
            preview  : {show : true,exec :function(){ 
                var s = window.open('','m','toolbar=no,location=no,directories=no,status=yes,menubar=yes,width=600,height=400,scrollbars=yes,resizable=yes') 
                s.document.writeln('<html><head><title>Rich Text Editor Preview</title><style>body{font:12px/1.5 Tahoma,Helvetica,Arial,sans-serif;}</style></head><body>'+this.content()+'</body></html>');
            }}
                      
        }
    });
})(jQuery);