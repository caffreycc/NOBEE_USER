/**
 * Created by aixiaoai on 2017/1/16.
 */
var CommonComponents = function () {
    //确认按钮
    var confirmationBtn = {
        mounted: function() {
            var self = this;
            $(self.$el).on('confirmed.bs.confirmation',function(){
                self.confirmation();
            });
        },
        template: '<button :id="id" :name="name" type="button" class="btn btn-danger" ' +
        'data-toggle="confirmation" data-placement="bottom" :data-title="title" ' +
        ':data-btn-ok-label="okLabel" :data-btn-cancel-label="cancelLabel" data-popout="true"><slot>' +
        '</slot>' +
        '<i class="fa fa-remov	e"></i>' +
        '</button>',
        props: ['id', 'name', 'title', 'okLabel', 'cancelLabel'],
        methods: {
            confirmation: function() {
                this.$emit('ok');
            }
        }
    };

    //数据表格
    var datatables = {
        mounted: function() {
            var self = this;
            $(this.$el).DataTable({
                //禁止排序
                ordering: self.order === undefined || self.order === null ? false : self.order,
                //固定参数
                serverSide: true,
                //表格元素排列,运用BootStrap栅格系统 l = 分页页数,f = 搜索框,B = 按钮, r = 加载进度条, t = 表格主体, i = 分页信息, p = 分页按钮
                dom: self.dom == undefined || self.dom == null ? "<'row'r><'table-scrollable't><'row datatables_page_info_row'<'col-sm-6 col-xs-12 datatables_page_info'i><'col-sm-12 col-xs-12 text-center'p>>" : self.dom,
                //dom: "<'row'<'col-sm-12 col-xs-12'l>r><'table-scrollable't><'row'<'col-sm-6 col-xs-12'i><'col-sm-6 col-xs-12'p>>",
                //服务器加载数据地址
                ajax:{
                    "url": self.ajaxUrl,
                    "data":function(d){
                        for(var key in self.ajaxData) {
                            d[key] = self.ajaxData[key]
                        }
                    }
                },
                //列定义ENERGY_TYPE
                "columns": self.columns
            });
        },
        template: '<table :id="id" :name="name" class="table table-striped table-bordered table-hover">' +
        '</table>',
        props: ['id', 'name', 'ajax-url', 'ajax-data', 'dom', 'columns', 'order'],
        methods: {
            reload: function() {
                $(this.$el).DataTable().ajax.reload();
            },
            getSelect: function() {
                return $(this.$el).DataTable().rows({selected:true});
            }
        }
    };

    //布局区域
    var portlet = {
        template: '<div class="portlet light bordered">' +
        '<div class="portlet-title">' +
        '<div class="caption">' +
        '<i class="font-blue-sharp" :class="ticon"></i>' +
        '<span name="title" class="caption-subject font-blue-sharp bold uppercase"><slot name="title"></slot></span>' +
        '</div>' +
        '<div name="tools" class="tools">' +
        '<slot name="tools"></slot>' +
        '</div>' +
        '</div>' +
        '<div class="portlet-body">' +
        '<slot name="body"></slot>' +
        '</div>' +
        '</div>',
        props: ['icon'],
        computed: {
            ticon: function() {
                if(this.icon === undefined || this.icon == null) {
                    return 'icon-wrench';
                }
                return this.icon;
            }
        }
    };

    //弹窗
    var modal = {
        template: '<div :id="id" :name="name" class="modal fade" :class="tModalSize" tabindex="-1" role="basic" aria-hidden="true">' +
        '<div class="modal-dialog" :class="tDialogSize">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>' +
        '<h4 class="modal-title"><slot name="title"></slot></h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<slot name="body"></slot>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<slot name="footer"></slot>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>',
        //modal-size
        props: ['id', 'name', 'modal-size'],
        methods: {
            show: function() {
                $(this.$el).modal("show");
            },
            hide: function() {
                $(this.$el).modal("hide");
            }
        },
        computed: {
            tModalSize: function() {
                if (this.modalSize) {
                    if (this.modalSize === 'sm') {
                        return "bs-modal-sm";
                    }

                    if (this.modalSize === 'lg') {
                        return "bs-modal-lg";
                    }
                }

                return "";
            },
            tDialogSize: function() {
                if (this.modalSize) {
                    if (this.modalSize === 'sm') {
                        return "modal-sm";
                    }

                    if (this.modalSize === 'lg') {
                        return "modal-lg";
                    }

                    if (this.modalSize === 'full') {
                        return "modal-full";
                    }
                }

                return "";
            }
        }
    };

    //单选按钮
    var radio = {
        template: '<label class="radio-inline">' +
        '<input :id="id" type="radio" :name="name" :value="val" ><slot></slot></label>',
        props: ['id', 'name', 'value', 'val'],
        mounted: function() {
            $("input:radio").uniform();
            var self = this;
            $(this.$el).find("input[type='radio']").change(function() {
                self.updateValue($(this).val());
            })
            this.checkRadio();
        },
        updated: function() {
            this.checkRadio();
        },
        methods: {
            checkRadio: function() {
                var temp = $(this.$el).find("input[type='radio']");
                if(this.value !== undefined && this.value !== null) {
                    if(this.value.toString() === temp.val()) {
                        temp.prop("checked", true);
                    } else {
                        temp.prop("checked", false);
                    }
                }
                $.uniform.update();
            },
            updateValue: function(value) {
                this.$emit('input', value);
            }
        }
    }

    //单选按钮组
    var radiogroup = {
        components: {
            'my-radio': radio
        },
        template: '<div :id="id" :name="name" class="radio-list">' +
        '<slot></slot>' +
        '<my-radio :name="radioName" :value="value" @input="updateValue" :val="radio.id" v-for="radio in radios">{{ radio.text }}</my-radio>' +
        '</div>',
        props: ['id', 'name', 'radio-name', 'ajax-url', 'ajax-data', 'value', 'item-id', 'item-text', 'items-path'],
        data: function() {
            return {
                radios: [],
            }
        },
        mounted: function() {
            var self = this;
            if(this.ajaxUrl != undefined && this.ajaxUrl != null) {
                $.post(this.ajaxUrl, this.ajaxData, function(response) {
                    response[self.itemsPath].forEach(function(se) {
                        self.radios.push({id: se[self.itemId], text: se[self.itemText] });
                    });
                })
            }
        },
        methods: {
            updateValue: function(value) {
                this.$emit('input', value);
            }
        }
    };

    //下拉框
    var select2 = {
        template: '<select :id="id" :name="name" :disabled="tdisabled" :value="value" class="bs-select form-control" :multiple="tmultiple" style="width: 100%;">' +
        '<slot></slot>' +
        '<option v-for="item in items" :value="item[itemId]">{{ item[itemText] }}</option>' +
        '</select>',
        //             "请选择字符串"(true false)    逻辑流               命名sql和条件      默认值          显示搜索框                                                                                                            返回对象名称         多选（true,false）
        props: ['id', 'name', 'disabled', 'pre-select', 'ajax-url', 'ajax-data',
            'value','search-box', 'placeholder', 'item-id', 'item-text', 'items-path', 'multiple', 'default'],
        mounted: function() {
            var self = this;

            //初始化控件
            $(self.$el).selectpicker({
                iconBase: 'fa',
                tickIcon: 'fa-check'
            });

            //监听事件
            $(self.$el).on('changed.bs.select', function (e) {
                self.updateValue($(this).val());
            });

            //服务器加载
            if (self.ajaxUrl != undefined && self.ajaxUrl != null) {
                self.load();
            }
        },
        updated: function () {
            $(this.$el).selectpicker("refresh");
            if (this.value) {
                $(this.$el).selectpicker('val', this.value);
            } else {
                $(this.$el).selectpicker('val', null);
            }
        },
        computed: {
            tmultiple: function() {
                if(this.multiple) {
                    return 'multiple';
                }
                return null;
            },
            tdisabled: function() {
                if(this.disabled) {
                    return false;
                }
                return this.disabled;
            },
            tsearchBox: function() {
                if(this.searchBox) {
                    if(self.searchBox.toString() === 'false') {
                        return null;
                    }
                    return 'Infinity';
                }

                return 'Infinity';
            },
            tplaceholder: function() {
                if(this.placeholder !== undefined) {
                    return self.placeholder;
                }

                return null;
            }
        },
        data: function() {
            return {
                items: []
            }
        },
        watch:{
            ajaxData: function () {
                this.load();
            }
        },
        methods: {
            load: function () {
                var self = this;
                if (self.ajaxUrl && self.ajaxUrl) {
                    $.post(self.ajaxUrl, self.ajaxData, function(response) {
                        if(response && response[self.itemsPath]) {
                            self.items = response[self.itemsPath];
                        }
                    })
                }
            },
            updateValue: function(value) {
                this.$emit('input', value);
            },
            defaultSelect: function() {
                if(this.items.length > 0) $(this.$el).selectpicker('val', this.items[0][this.itemId]);
            }
        }
    };

    //表单
    var form = {
        template: '<form :id="id" slot="body" :name="name" :action="action" :method="method" ' +
        ':enctype="enctypeValue" class="from-horizontal" role="form"><slot></slot></form>',
        props: ['id', 'name', 'action', 'method', 'is-file', 'rules'],
        data: function() {
            return {
                validate: null
            }
        },
        mounted: function() {
            var slef = this;
            slef.validate = $(this.$el).validate({
                rules: slef.rules
            });
        },
        computed: {
            enctypeValue: function() {
                if (this.isFile != undefined && this.isFile != null && this.isFile.toString() === 'true') {
                    return "multipart/form-data";
                }
                return "application/x-www-form-urlencoded";
            }
        },
        methods: {
            valid: function() {
                return $(this.$el).valid();
            },
            resetForm: function() {
                $(this.$el).resetForm();
            },
            ajaxSubmit: function(option) {
                $(this.$el).ajaxSubmit(option);
            },
            resetValiForm: function() {
                // this.validate.resetForm();
                $(this.$el).find("div.form-group").removeClass("has-error");
            }
        }
    };

    //复选框
    var checkbox = {
        template: '<label class="checkbox-inline"><input type="checkbox" :id="id" :value="val"><slot></slot></label>',
        props: ['id', 'name', 'val'],
        mounted: function() {
            $("input:checkbox").uniform();
            var self = this;$(this.$el).find("input[type='checkbox']").change(function() {
                self.$emit('input', this.checked, $(this).val());
            })
        }
    };

    //复选框组
    var checkboxgroup = {
        components: {
            'my-checkbox': checkbox
        },
        template: '<div class="checkbox-list">' +
        '<slot></slot>' +
        '<my-checkbox :name="checkboxName" :val="checkbox.id" @input="updateValue" v-for="checkbox in checkboxs">{{ checkbox.text }}</my-checkbox>' +
        '</div>',
        props: ['id', 'name', 'checkbox-name', 'ajax-url', 'ajax-data', 'value', 'item-id', 'item-text', 'items-path'],
        data: function() {
            return {
                checkboxs: [],
                values: [],
                isupdate: true
            }
        },
        mounted: function() {
            var self = this;
            self.isupdate = true;
            if(self.ajaxUrl != undefined && self.ajaxUrl != null) {
                $.post(this.ajaxUrl, this.ajaxData, function(response) {
                    response[self.itemsPath].forEach(function(se) {
                        self.checkboxs.push({id: se[self.itemId], text: se[self.itemText] });
                    });
                })
            }
            $(self.$el).find('input[type="checkbox"]').change(function() {
                self.updateValue(this.checked, $(this).val());
            })

            self.checkValue();
        },
        updated: function() {
            this.checkValue();
        },
        methods: {
            checkValue: function() {
                var self = this;
                var checkboxs = $(self.$el).find('input[type="checkbox"]');
                var values = [];
                values = values.concat(this.value);
                for (var i = 0; i < checkboxs.length; i++) {
                    var temp = $(checkboxs[i]);
                    temp.prop("checked", false);
                    values.forEach(function(item) {
                        if(item === temp.val()) {
                            temp.prop("checked", true);
                        }
                    })
                }

                $.uniform.update();

            },
            updateValue: function(type, value) {
                this.values = [];
                this.values = this.values.concat(this.value);
                if(type) {
                    this.values.push(value);
                } else {
                    this.values.pop(value);
                }
                this.$emit("input", this.values);
            }
        }
    };

    //时间范围组件
    var daterange = {
        mounted: function() {
            var self = this;

            //初始化时间选择控件
            $(".date-picker").datepicker({
                format: "yyyy-mm-dd",
                language: 'zh-CN'
            }).on('changeDate', function(ev){
                self.updateValue($(ev.target).attr("flag"), $(ev.target).val());
            });
        },
        template: '<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy/mm/dd">' +
        '<input type="text" flag="start" :value="value.starttime" class="form-control" name="from">' +
        '<span class="input-group-addon"> to </span>' +
        '<input type="text" flag="end" :value="value.endtime" class="form-control" name="to"> ' +
        '</div>',
        props: ['value'],
        data: function() {
            return {
                daterange: {
                    starttime: null,
                    endtime: null
                }
            }
        },
        methods: {
            updateValue: function(type, date) {
                if (type === 'start') {
                    this.daterange.starttime = date;
                } else {
                    this.daterange.endtime = date;
                }
                this.$emit('input', this.daterange);
            }
        }
    };

    //时间带时分秒的组件
    var datetimepicker = {
        mounted: function() {
            var self = this;
            //初始化时间选择控件
            $(self.$el).datetimepicker({
                autoclose: true,
                format: "yyyy-mm-dd hh:ii:ss",
                language: 'zh-CN'
            }).on('changeDate', function(ev){
                self.updateValue($(ev.target).children('input').val());
            });
        },
        template:'<div id="user-time-form" class="input-group date form_datetime">'+
        '<input type="text" size="16" :name="name" :value="value" class="form-control" readonly="">'+
        '<span class="input-group-btn">'+
        '<button class="btn default date-set" type="button">'+
        '<i class="fa fa-calendar"></i>'+
        '</button>'+
        '</span>'+
        '</div>',

        props: ['name', 'value'],
        methods: {
            updateValue: function(date) {
                this.$emit('input', date);
            }
        }
    };

    //日期选择控件2
    var daterange2 = {
        template: '<div>' +
        '<input type="hidden" flag="start" class="form-control" :name="startName">' +
        '<input type="hidden" flag="end" class="form-control" :name="endName">' +
        '<div class="input-group daterange2" :id="id">' +
        '<input type="text" class="form-control">' +
        '<span class="input-group-btn">' +
        '<button class="btn default date-range-toggle" type="button">' +
        '<i class="fa fa-calendar"></i>' +
        '</button>' +
        '</span>' +
        '</div>' +
        '</div>',
        props: ['id', 'start-name', 'end-name', 'min-date', 'max-date', 'limit-days', 'ranges', 'format', 'value'],
        mounted: function() {
            var self = this;
            //初始化时间范围选择
            $(this.$el).children('.daterange2').daterangepicker({
                    //startDate: moment().startOf('month'),
                    //endDate: moment().endOf('month'),
                    minDate: self.tminDate,
                    maxDate: self.tmaxDate,
                    dateLimit: {
                        days: self.tlimitDays
                    },
                    showDropdowns: true,
                    showWeekNumbers: true,
                    timePicker: false,
                    autoUpdateInput: true,
                    ranges: self.tRanges,
                    buttonClasses: ['btn'],
                    applyClass: 'green',
                    cancelClass: 'default',
                    separator: ' to ',
                    locale: {
                        format: self.tFormat,
                        applyLabel: '应用',
                        cancelLabel: '取消',
                        fromLabel: '从',
                        toLabel: '至',
                        weekLabel: '周',
                        customRangeLabel: '自定义设置',
                        daysOfWeek: ['一', '二', '三', '四', '五', '六', '日'],
                        monthNames: ['一月份', '二月份', '三月份', '四月份', '五月份', '六月份', '七月份', '八月份', '九月份', '十月份', '十一月份', '十二月份'],
                        firstDay: 1
                    }
                },
                function (start, end) {
                    $(self.$el).children('.daterange2').children('input').val(start.format('YYYY年MM月DD日') + ' - ' + end.format('YYYY年MM月DD日'));
                    $(self.$el).children('input[flag="start"]').val(start.format(self.tFormat));
                    $(self.$el).children('input[flag="end"]').val(end.format(self.tFormat));
                    self.updateValue({starttime: start.format(self.tFormat), endtime: end.format(self.tFormat)});
                });
        },
        watch: {
            'value.starttime': function() {
                //更新日期
                this.updateDate();
            },
            'value.endtime': function() {
                //更新日期
                this.updateDate();
            }
        },
        computed: {
            tstartTime: function() {
                if (this.value === undefined || this.value === null || this.value.starttime === undefined || this.value.starttime === null) {
                    return moment().startOf('month');
                }

                return this.value.starttime;
            },
            tendTime: function() {
                if (this.value === undefined || this.value === null || this.value.endtime === undefined || this.value.endtime === null) {
                    return moment().endOf('month');
                }

                return this.value.endtime;
            },
            tFormat: function() {
                if (this.format === undefined || this.format === null) {
                    return 'YYYY-MM-DD';
                }

                return this.format;
            },
            tlimitDays: function() {
                if (this.limitDays === undefined || this.limitDays === null) {
                    return 365;
                }

                return this.limitDays;
            },
            tRanges: function() {
                if (this.ranges === undefined || this.ranges === null) {
                    return {
                        '今天': [moment(), moment()],
                        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                        '最后的7天': [moment().subtract(6, 'days'), moment()],
                        '最后的30天': [moment().subtract(29, 'days'), moment()],
                        '本月': [moment().startOf('month'), moment().endOf('month')],
                        '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    };
                }

                return this.ranges;
            },
            tminDate: function() {
                if (this.minDate === undefined || this.minDate === null) {
                    return '2000-01-01';
                }

                return this.minDate;
            },
            tmaxDate: function() {
                if (this.maxDate === undefined || this.maxDate === null) {
                    return '2100-01-01';
                }

                return this.maxDate;
            }
        },
        methods: {
            updateDate: function() {
                //默认时间
                $(this.$el).children('.daterange2').children('input').val(moment(this.tstartTime).format('YYYY年MM月DD日') + ' - ' + moment(this.tendTime).format('YYYY年MM月DD日'));
                $(this.$el).children('input[flag="start"]').val(this.tstartTime);
                $(this.$el).children('input[flag="end"]').val(this.tendTime);
            },
            updateValue: function(value) {
                this.$emit("input", value);
            }
        }
    };

    var datepicker = {
        template:'<div class="input-group input-medium date date-picker" data-date-format="yyyy-mm-dd">'+
        '<input type="text" :name="name" :value="value" class="form-control" readonly="">'+
        '<span class="input-group-btn">'+
        '<button class="btn default" type="button">'+
        '<i class="fa fa-calendar"></i>'+
        '</button>'+
        '</span>'+
        '</div>',
        mounted:function () {
            var self = this;
            //初始化时间选择控件
            $(self.$el).datepicker({
                autoclose: true,
                language: 'zh-CN',
                rtl: App.isRTL(),
                orientation: "left",
                endDate:"2200-01-01",
                startDate:"1937-01-01"
            }).on('changeDate', function(ev){
                self.updateValue($(ev.target).children('input').val());
            });
        },
        updated: function() {
            console.log(this.value)
            $(this.$el).datepicker('setDate', this.value);
        },
        props: ['name', 'value'],
        methods:{
            getValue:function () {
                return this.$emit('input');
            },
            updateValue: function(date) {
                this.$emit('input', date);
            }
        }
    }

    var fileinput = {
        template:'<div :id="id" class="fileinput fileinput-new" data-provides="fileinput">' +
        '<div class="input-group input-large">' +
        '<div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">' +
        '<i class="fa fa-file fileinput-exists"></i>&nbsp;' +
        '<span class="fileinput-filename"> </span>' +
        '</div>' +
        '<span class="input-group-addon btn default btn-file">' +
        '<span class="fileinput-new"> {{ selectLabel }} </span>' +
        '<span class="fileinput-exists"> {{ editLabel }} </span>' +
        '<input type="file" :name="name"> </span>' +
        '<a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput"> {{ removeLabel }} </a>' +
        '</div>' +
        '</div>',
        mounted:function () {
            //初始化控件
            $(this.$el).fileinput()
        },
        props: ['id', 'name', 'select-label', 'edit-label', 'remove-label'],
        methods:{
            reset:function () {
                $(this.$el).fileinput('reset');
            },
            clear: function(date) {
                $(this.$el).fileinput('clear');
            },
            getValue:function () {
                return  $(this.$el).find("input[type='file']").val();
            },
            setValue: function (fileName) {
                if (fileName != null) {
                    var fileNames = fileName.split("/");
                    fileNames = fileName.split("\\");
                    $(this.$el).find('.fileinput-filename').text(fileNames[fileNames.length-1]);
                }
            },
            getText: function () {
                return $(this.$el).find('.fileinput-filename').text();
            }
        }
    }

    return {
        confirmationBtn: confirmationBtn,
        datatables: datatables,
        portlet: portlet,
        modal: modal,
        radiogroup: radiogroup,
        radio: radio,
        select2: select2,
        form: form,
        checkboxgroup: checkboxgroup,
        checkbox: checkbox,
        daterange: daterange,
        datetimepicker: datetimepicker,
        daterange2: daterange2,
        datepicker: datepicker,
        fileinput: fileinput
    };
}();