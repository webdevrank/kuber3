// [AIV_SHORT]  Build version: 2.2.2 - Tuesday, April 14th, 2020, 1:35:36 PM  
CobrowseIO = function(e) {
    var t = {};

    function n(r) {
        if (t[r]) return t[r].exports;
        var o = t[r] = {
            i: r,
            l: !1,
            exports: {}
        };
        return e[r].call(o.exports, o, o.exports, n), o.l = !0, o.exports
    }
    return n.m = e, n.c = t, n.d = function(e, t, r) {
        n.o(e, t) || Object.defineProperty(e, t, {
            enumerable: !0,
            get: r
        })
    }, n.r = function(e) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {
            value: "Module"
        }), Object.defineProperty(e, "__esModule", {
            value: !0
        })
    }, n.t = function(e, t) {
        if (1 & t && (e = n(e)), 8 & t) return e;
        if (4 & t && "object" == typeof e && e && e.__esModule) return e;
        var r = Object.create(null);
        if (n.r(r), Object.defineProperty(r, "default", {
                enumerable: !0,
                value: e
            }), 2 & t && "string" != typeof e)
            for (var o in e) n.d(r, o, function(t) {
                return e[t]
            }.bind(null, o));
        return r
    }, n.n = function(e) {
        var t = e && e.__esModule ? function() {
            return e.default
        } : function() {
            return e
        };
        return n.d(t, "a", t), t
    }, n.o = function(e, t) {
        return Object.prototype.hasOwnProperty.call(e, t)
    }, n.p = "", n(n.s = 158)
}([function(e, t, n) {
    var r = n(3),
        o = n(41).f,
        i = n(27),
        a = n(29),
        c = n(89),
        s = n(116),
        u = n(120);
    e.exports = function(e, t) {
        var n, f, l, d, p, h = e.target,
            v = e.global,
            y = e.stat;
        if (n = v ? r : y ? r[h] || c(h, {}) : (r[h] || {}).prototype)
            for (f in t) {
                if (d = t[f], l = e.noTargetGet ? (p = o(n, f)) && p.value : n[f], !u(v ? f : h + (y ? "." : "#") + f, e.forced) && void 0 !== l) {
                    if (typeof d == typeof l) continue;
                    s(d, l)
                }(e.sham || l && l.sham) && i(d, "sham", !0), a(n, f, d, e)
            }
    }
}, function(e, t) {
    e.exports = function(e) {
        try {
            return !!e()
        } catch (e) {
            return !0
        }
    }
}, function(e, t, n) {
    "use strict";
    var r, o = n(152),
        i = n(9),
        a = n(3),
        c = n(10),
        s = n(11),
        u = n(72),
        f = n(27),
        l = n(29),
        d = n(16).f,
        p = n(60),
        h = n(75),
        v = n(4),
        y = n(66),
        g = a.Int8Array,
        m = g && g.prototype,
        b = a.Uint8ClampedArray,
        w = b && b.prototype,
        _ = g && p(g),
        O = m && p(m),
        E = Object.prototype,
        S = E.isPrototypeOf,
        x = v("toStringTag"),
        C = y("TYPED_ARRAY_TAG"),
        j = o && !!h && "Opera" !== u(a.opera),
        A = !1,
        k = {
            Int8Array: 1,
            Uint8Array: 1,
            Uint8ClampedArray: 1,
            Int16Array: 2,
            Uint16Array: 2,
            Int32Array: 4,
            Uint32Array: 4,
            Float32Array: 4,
            Float64Array: 8
        },
        T = function(e) {
            return c(e) && s(k, u(e))
        };
    for (r in k) a[r] || (j = !1);
    if ((!j || "function" != typeof _ || _ === Function.prototype) && (_ = function() {
            throw TypeError("Incorrect invocation")
        }, j))
        for (r in k) a[r] && h(a[r], _);
    if ((!j || !O || O === E) && (O = _.prototype, j))
        for (r in k) a[r] && h(a[r].prototype, O);
    if (j && p(w) !== O && h(w, O), i && !s(O, x))
        for (r in A = !0, d(O, x, {
                get: function() {
                    return c(this) ? this[C] : void 0
                }
            }), k) a[r] && f(a[r], C, r);
    e.exports = {
        NATIVE_ARRAY_BUFFER_VIEWS: j,
        TYPED_ARRAY_TAG: A && C,
        aTypedArray: function(e) {
            if (T(e)) return e;
            throw TypeError("Target is not a typed array")
        },
        aTypedArrayConstructor: function(e) {
            if (h) {
                if (S.call(_, e)) return e
            } else
                for (var t in k)
                    if (s(k, r)) {
                        var n = a[t];
                        if (n && (e === n || S.call(n, e))) return e
                    } throw TypeError("Target is not a typed array constructor")
        },
        exportTypedArrayMethod: function(e, t, n) {
            if (i) {
                if (n)
                    for (var r in k) {
                        var o = a[r];
                        o && s(o.prototype, e) && delete o.prototype[e]
                    }
                O[e] && !n || l(O, e, n ? t : j && m[e] || t)
            }
        },
        exportTypedArrayStaticMethod: function(e, t, n) {
            var r, o;
            if (i) {
                if (h) {
                    if (n)
                        for (r in k)(o = a[r]) && s(o, e) && delete o[e];
                    if (_[e] && !n) return;
                    try {
                        return l(_, e, n ? t : j && g[e] || t)
                    } catch (e) {}
                }
                for (r in k) !(o = a[r]) || o[e] && !n || l(o, e, t)
            }
        },
        isView: function(e) {
            var t = u(e);
            return "DataView" === t || s(k, t)
        },
        isTypedArray: T,
        TypedArray: _,
        TypedArrayPrototype: O
    }
}, function(e, t, n) {
    (function(t) {
        var n = function(e) {
            return e && e.Math == Math && e
        };
        e.exports = n("object" == typeof globalThis && globalThis) || n("object" == typeof window && window) || n("object" == typeof self && self) || n("object" == typeof t && t) || Function("return this")()
    }).call(this, n(110))
}, function(e, t, n) {
    var r = n(3),
        o = n(90),
        i = n(11),
        a = n(66),
        c = n(94),
        s = n(123),
        u = o("wks"),
        f = r.Symbol,
        l = s ? f : f && f.withoutSetter || a;
    e.exports = function(e) {
        return i(u, e) || (c && i(f, e) ? u[e] = f[e] : u[e] = l("Symbol." + e)), u[e]
    }
}, function(e, t, n) {
    "use strict";
    var r = n(28),
        o = n(98),
        i = n(74),
        a = n(35),
        c = n(100),
        s = a.set,
        u = a.getterFor("Array Iterator");
    e.exports = c(Array, "Array", (function(e, t) {
        s(this, {
            type: "Array Iterator",
            target: r(e),
            index: 0,
            kind: t
        })
    }), (function() {
        var e = u(this),
            t = e.target,
            n = e.kind,
            r = e.index++;
        return !t || r >= t.length ? (e.target = void 0, {
            value: void 0,
            done: !0
        }) : "keys" == n ? {
            value: r,
            done: !1
        } : "values" == n ? {
            value: t[r],
            done: !1
        } : {
            value: [r, t[r]],
            done: !1
        }
    }), "values"), i.Arguments = i.Array, o("keys"), o("values"), o("entries")
}, function(e, t, n) {
    var r = n(95),
        o = n(29),
        i = n(160);
    r || o(Object.prototype, "toString", i, {
        unsafe: !0
    })
}, function(e, t, n) {
    "use strict";
    n(5), n(49), n(30), n(6), n(8), n(129), n(12), Object.defineProperty(t, "__esModule", {
        value: !0
    }), Object.defineProperty(t, "CompressionError", {
        enumerable: !0,
        get: function() {
            return r.default
        }
    }), Object.defineProperty(t, "VirtualDOM", {
        enumerable: !0,
        get: function() {
            return o.default
        }
    }), Object.defineProperty(t, "depthFirst", {
        enumerable: !0,
        get: function() {
            return i.default
        }
    }), Object.defineProperty(t, "depthFirstPostOrder", {
        enumerable: !0,
        get: function() {
            return i.depthFirstPostOrder
        }
    }), Object.defineProperty(t, "isxdoc", {
        enumerable: !0,
        get: function() {
            return a.default
        }
    }), Object.defineProperty(t, "parents", {
        enumerable: !0,
        get: function() {
            return c.default
        }
    }), Object.defineProperty(t, "PropertyObserver", {
        enumerable: !0,
        get: function() {
            return s.default
        }
    });
    var r = f(n(133)),
        o = f(n(174)),
        i = function(e) {
            if (e && e.__esModule) return e;
            var t = u();
            if (t && t.has(e)) return t.get(e);
            var n = {};
            if (null != e) {
                var r = Object.defineProperty && Object.getOwnPropertyDescriptor;
                for (var o in e)
                    if (Object.prototype.hasOwnProperty.call(e, o)) {
                        var i = r ? Object.getOwnPropertyDescriptor(e, o) : null;
                        i && (i.get || i.set) ? Object.defineProperty(n, o, i) : n[o] = e[o]
                    }
            }
            n.default = e, t && t.set(e, n);
            return n
        }(n(139)),
        a = f(n(178)),
        c = f(n(179)),
        s = f(n(182));

    function u() {
        if ("function" != typeof WeakMap) return null;
        var e = new WeakMap;
        return u = function() {
            return e
        }, e
    }

    function f(e) {
        return e && e.__esModule ? e : {
            default: e
        }
    }
}, function(e, t, n) {
    "use strict";
    var r = n(101).charAt,
        o = n(35),
        i = n(100),
        a = o.set,
        c = o.getterFor("String Iterator");
    i(String, "String", (function(e) {
        a(this, {
            type: "String Iterator",
            string: String(e),
            index: 0
        })
    }), (function() {
        var e, t = c(this),
            n = t.string,
            o = t.index;
        return o >= n.length ? {
            value: void 0,
            done: !0
        } : (e = r(n, o), t.index += e.length, {
            value: e,
            done: !1
        })
    }))
}, function(e, t, n) {
    var r = n(1);
    e.exports = !r((function() {
        return 7 != Object.defineProperty({}, 1, {
            get: function() {
                return 7
            }
        })[1]
    }))
}, function(e, t) {
    e.exports = function(e) {
        return "object" == typeof e ? null !== e : "function" == typeof e
    }
}, function(e, t) {
    var n = {}.hasOwnProperty;
    e.exports = function(e, t) {
        return n.call(e, t)
    }
}, function(e, t, n) {
    var r = n(3),
        o = n(132),
        i = n(5),
        a = n(27),
        c = n(4),
        s = c("iterator"),
        u = c("toStringTag"),
        f = i.values;
    for (var l in o) {
        var d = r[l],
            p = d && d.prototype;
        if (p) {
            if (p[s] !== f) try {
                a(p, s, f)
            } catch (e) {
                p[s] = f
            }
            if (p[u] || a(p, u, l), o[l])
                for (var h in i)
                    if (p[h] !== i[h]) try {
                        a(p, h, i[h])
                    } catch (e) {
                        p[h] = i[h]
                    }
        }
    }
}, function(e, t, n) {
    var r = n(42),
        o = Math.min;
    e.exports = function(e) {
        return e > 0 ? o(r(e), 9007199254740991) : 0
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(3),
        i = n(56),
        a = n(55),
        c = n(9),
        s = n(94),
        u = n(123),
        f = n(1),
        l = n(11),
        d = n(70),
        p = n(10),
        h = n(17),
        v = n(22),
        y = n(28),
        g = n(65),
        m = n(47),
        b = n(59),
        w = n(73),
        _ = n(68),
        O = n(175),
        E = n(92),
        S = n(41),
        x = n(16),
        C = n(82),
        j = n(27),
        A = n(29),
        k = n(90),
        T = n(83),
        P = n(67),
        L = n(66),
        I = n(4),
        R = n(134),
        M = n(135),
        D = n(48),
        N = n(35),
        F = n(21).forEach,
        U = T("hidden"),
        z = I("toPrimitive"),
        B = N.set,
        V = N.getterFor("Symbol"),
        W = Object.prototype,
        K = o.Symbol,
        q = i("JSON", "stringify"),
        Y = S.f,
        X = x.f,
        H = O.f,
        $ = C.f,
        G = k("symbols"),
        J = k("op-symbols"),
        Z = k("string-to-symbol-registry"),
        Q = k("symbol-to-string-registry"),
        ee = k("wks"),
        te = o.QObject,
        ne = !te || !te.prototype || !te.prototype.findChild,
        re = c && f((function() {
            return 7 != b(X({}, "a", {
                get: function() {
                    return X(this, "a", {
                        value: 7
                    }).a
                }
            })).a
        })) ? function(e, t, n) {
            var r = Y(W, t);
            r && delete W[t], X(e, t, n), r && e !== W && X(W, t, r)
        } : X,
        oe = function(e, t) {
            var n = G[e] = b(K.prototype);
            return B(n, {
                type: "Symbol",
                tag: e,
                description: t
            }), c || (n.description = t), n
        },
        ie = u ? function(e) {
            return "symbol" == typeof e
        } : function(e) {
            return Object(e) instanceof K
        },
        ae = function(e, t, n) {
            e === W && ae(J, t, n), h(e);
            var r = g(t, !0);
            return h(n), l(G, r) ? (n.enumerable ? (l(e, U) && e[U][r] && (e[U][r] = !1), n = b(n, {
                enumerable: m(0, !1)
            })) : (l(e, U) || X(e, U, m(1, {})), e[U][r] = !0), re(e, r, n)) : X(e, r, n)
        },
        ce = function(e, t) {
            h(e);
            var n = y(t),
                r = w(n).concat(le(n));
            return F(r, (function(t) {
                c && !se.call(n, t) || ae(e, t, n[t])
            })), e
        },
        se = function(e) {
            var t = g(e, !0),
                n = $.call(this, t);
            return !(this === W && l(G, t) && !l(J, t)) && (!(n || !l(this, t) || !l(G, t) || l(this, U) && this[U][t]) || n)
        },
        ue = function(e, t) {
            var n = y(e),
                r = g(t, !0);
            if (n !== W || !l(G, r) || l(J, r)) {
                var o = Y(n, r);
                return !o || !l(G, r) || l(n, U) && n[U][r] || (o.enumerable = !0), o
            }
        },
        fe = function(e) {
            var t = H(y(e)),
                n = [];
            return F(t, (function(e) {
                l(G, e) || l(P, e) || n.push(e)
            })), n
        },
        le = function(e) {
            var t = e === W,
                n = H(t ? J : y(e)),
                r = [];
            return F(n, (function(e) {
                !l(G, e) || t && !l(W, e) || r.push(G[e])
            })), r
        };
    (s || (A((K = function() {
        if (this instanceof K) throw TypeError("Symbol is not a constructor");
        var e = arguments.length && void 0 !== arguments[0] ? String(arguments[0]) : void 0,
            t = L(e),
            n = function(e) {
                this === W && n.call(J, e), l(this, U) && l(this[U], t) && (this[U][t] = !1), re(this, t, m(1, e))
            };
        return c && ne && re(W, t, {
            configurable: !0,
            set: n
        }), oe(t, e)
    }).prototype, "toString", (function() {
        return V(this).tag
    })), A(K, "withoutSetter", (function(e) {
        return oe(L(e), e)
    })), C.f = se, x.f = ae, S.f = ue, _.f = O.f = fe, E.f = le, R.f = function(e) {
        return oe(I(e), e)
    }, c && (X(K.prototype, "description", {
        configurable: !0,
        get: function() {
            return V(this).description
        }
    }), a || A(W, "propertyIsEnumerable", se, {
        unsafe: !0
    }))), r({
        global: !0,
        wrap: !0,
        forced: !s,
        sham: !s
    }, {
        Symbol: K
    }), F(w(ee), (function(e) {
        M(e)
    })), r({
        target: "Symbol",
        stat: !0,
        forced: !s
    }, {
        for: function(e) {
            var t = String(e);
            if (l(Z, t)) return Z[t];
            var n = K(t);
            return Z[t] = n, Q[n] = t, n
        },
        keyFor: function(e) {
            if (!ie(e)) throw TypeError(e + " is not a symbol");
            if (l(Q, e)) return Q[e]
        },
        useSetter: function() {
            ne = !0
        },
        useSimple: function() {
            ne = !1
        }
    }), r({
        target: "Object",
        stat: !0,
        forced: !s,
        sham: !c
    }, {
        create: function(e, t) {
            return void 0 === t ? b(e) : ce(b(e), t)
        },
        defineProperty: ae,
        defineProperties: ce,
        getOwnPropertyDescriptor: ue
    }), r({
        target: "Object",
        stat: !0,
        forced: !s
    }, {
        getOwnPropertyNames: fe,
        getOwnPropertySymbols: le
    }), r({
        target: "Object",
        stat: !0,
        forced: f((function() {
            E.f(1)
        }))
    }, {
        getOwnPropertySymbols: function(e) {
            return E.f(v(e))
        }
    }), q) && r({
        target: "JSON",
        stat: !0,
        forced: !s || f((function() {
            var e = K();
            return "[null]" != q([e]) || "{}" != q({
                a: e
            }) || "{}" != q(Object(e))
        }))
    }, {
        stringify: function(e, t, n) {
            for (var r, o = [e], i = 1; arguments.length > i;) o.push(arguments[i++]);
            if (r = t, (p(t) || void 0 !== e) && !ie(e)) return d(t) || (t = function(e, t) {
                if ("function" == typeof r && (t = r.call(this, e, t)), !ie(t)) return t
            }), o[1] = t, q.apply(null, o)
        }
    });
    K.prototype[z] || j(K.prototype, z, K.prototype.valueOf), D(K, "Symbol"), P[U] = !0
}, function(e, t, n) {
    "use strict";
    n(32), n(5), n(195), n(6), n(8), n(12);
    var r = "abcdefghijklmnopqrstuvwxyz",
        o = "".concat("0123456789").concat(r.toLowerCase()).concat(r.toUpperCase());
    var i = n(46);

    function a(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), a(this, "_idMap", new Map), a(this, "_nodeMap", new Map), a(this, "_currentId", 0), a(this, "_nextId", (function() {
            return t._currentId += 1, t._currentId
        })), a(this, "track", (function(e) {
            if (!e) return null;
            if (!i.a.isDOMNode(e)) return e.id;
            if (!t.get(e)) {
                var n = e.nodeType === window.Node.DOCUMENT_NODE ? function(e) {
                    for (var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : o, n = "", r = e; r > 0; r -= 1) n += t[Math.floor(Math.random() * t.length)];
                    return n
                }(5) : "".concat(t.track(document), "-").concat(t._nextId());
                t._idMap.set(n, e), t._nodeMap.set(e, n)
            }
            return t._nodeMap.get(e)
        })), a(this, "get", (function(e) {
            return e ? i.a.isDOMNode(e) ? t._nodeMap.get(e) || null : e.id : null
        })), a(this, "getById", (function(e) {
            return e && t._idMap.get(e) || null
        })), a(this, "forget", (function(e) {
            t._idMap.delete(t.get(e)), t._nodeMap.delete(e)
        }))
    }
}, function(e, t, n) {
    var r = n(9),
        o = n(111),
        i = n(17),
        a = n(65),
        c = Object.defineProperty;
    t.f = r ? c : function(e, t, n) {
        if (i(e), t = a(t, !0), i(n), o) try {
            return c(e, t, n)
        } catch (e) {}
        if ("get" in n || "set" in n) throw TypeError("Accessors not supported");
        return "value" in n && (e[t] = n.value), e
    }
}, function(e, t, n) {
    var r = n(10);
    e.exports = function(e) {
        if (!r(e)) throw TypeError(String(e) + " is not an object");
        return e
    }
}, function(e, t, n) {
    "use strict";
    var r = n(183),
        o = {};
    t.a = {
        set: function(e, t) {
            ! function e() {
                window.localStorage || e.done || (e.done = !0, console.warn("Warning: localStorage is not supported. Cobrowse falling back to cookie or in-memory storage and may not function as expected."))
            }();
            var n = JSON.stringify({
                value: t
            });
            window.localStorage ? window.localStorage.setItem(e, n) : r.set(e, t), o[e] = t
        },
        get: function(e) {
            try {
                if (window.localStorage) {
                    var t = window.localStorage && window.localStorage.getItem(e);
                    return JSON.parse(t).value
                }
                var n = r.getJSON(e);
                return n || o[e]
            } catch (e) {
                return null
            }
        },
        remove: function(e) {
            window.localStorage && window.localStorage.removeItem(e), r.remove(e), o[e] = void 0
        }
    }
}, function(e, t, n) {
    "use strict";
    n(14), n(23), n(24), n(5), n(40), n(6), n(8), n(12);
    var r = n(25),
        o = n(242),
        i = n(18);

    function a(e) {
        return (a = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function c(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function s(e) {
        return (s = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function u(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function f(e, t) {
        return (f = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function l(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var d = n(37)("cbio.WindowTracking"),
        p = function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), e = function(e, t) {
                    return !t || "object" !== a(t) && "function" != typeof t ? u(e) : t
                }(this, s(t).call(this)), l(u(e), "start", (function() {
                    e._started || (e._started = !0, d("started"), e._evaluateActive(), document.addEventListener("visibilitychange", e._evaluateActive, {
                        capture: !0
                    }), document.addEventListener("mouseenter", e._evaluateActive, {
                        capture: !0
                    }))
                })), l(u(e), "stop", (function() {
                    d("stopped"), e.removeAllListeners(), e._started = !1, document.removeEventListener("visibilitychange", e._evaluateActive, {
                        capture: !0
                    }), document.removeEventListener("mouseenter", e._evaluateActive, {
                        capture: !0
                    })
                })), l(u(e), "isActive", (function() {
                    return i.a.get("_cobrowse_window_id") === e.id
                })), l(u(e), "setTracksIframes", (function(t) {
                    e._tracksIframes = t, e._evaluateActive()
                })), l(u(e), "shouldTrack", (function() {
                    return !document.hidden && (!(window.self !== window.top) || e._tracksIframes)
                })), l(u(e), "makeActive", (function() {
                    e.isActive() || (d("making active", window), i.a.set("_cobrowse_window_id", e.id), e.emit("activate", window))
                })), l(u(e), "_evaluateActive", (function() {
                    e.shouldTrack() && e.makeActive()
                })), e._tracksIframes = !1, e._started = !1, e
            }
            var n, r, p;
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && f(e, t)
            }(t, e), n = t, (r = [{
                key: "id",
                get: function() {
                    return this._id || (this._id = Object(o.a)()), this._id
                }
            }]) && c(n.prototype, r), p && c(n, p), t
        }(r.EventEmitter);
    t.a = new p
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(121);
    r({
        target: "Array",
        proto: !0,
        forced: [].forEach != o
    }, {
        forEach: o
    })
}, function(e, t, n) {
    var r = n(58),
        o = n(63),
        i = n(22),
        a = n(13),
        c = n(122),
        s = [].push,
        u = function(e) {
            var t = 1 == e,
                n = 2 == e,
                u = 3 == e,
                f = 4 == e,
                l = 6 == e,
                d = 5 == e || l;
            return function(p, h, v, y) {
                for (var g, m, b = i(p), w = o(b), _ = r(h, v, 3), O = a(w.length), E = 0, S = y || c, x = t ? S(p, O) : n ? S(p, 0) : void 0; O > E; E++)
                    if ((d || E in w) && (m = _(g = w[E], E, b), e))
                        if (t) x[E] = m;
                        else if (m) switch (e) {
                    case 3:
                        return !0;
                    case 5:
                        return g;
                    case 6:
                        return E;
                    case 2:
                        s.call(x, g)
                } else if (f) return !1;
                return l ? -1 : u || f ? f : x
            }
        };
    e.exports = {
        forEach: u(0),
        map: u(1),
        filter: u(2),
        some: u(3),
        every: u(4),
        find: u(5),
        findIndex: u(6)
    }
}, function(e, t, n) {
    var r = n(54);
    e.exports = function(e) {
        return Object(r(e))
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(9),
        i = n(3),
        a = n(11),
        c = n(10),
        s = n(16).f,
        u = n(116),
        f = i.Symbol;
    if (o && "function" == typeof f && (!("description" in f.prototype) || void 0 !== f().description)) {
        var l = {},
            d = function() {
                var e = arguments.length < 1 || void 0 === arguments[0] ? void 0 : String(arguments[0]),
                    t = this instanceof d ? new f(e) : void 0 === e ? f() : f(e);
                return "" === e && (l[t] = !0), t
            };
        u(d, f);
        var p = d.prototype = f.prototype;
        p.constructor = d;
        var h = p.toString,
            v = "Symbol(test)" == String(f("test")),
            y = /^Symbol\((.*)\)[^)]+$/;
        s(p, "description", {
            configurable: !0,
            get: function() {
                var e = c(this) ? this.valueOf() : this,
                    t = h.call(e);
                if (a(l, e)) return "";
                var n = v ? t.slice(7, -1) : t.replace(y, "$1");
                return "" === n ? void 0 : n
            }
        }), r({
            global: !0,
            forced: !0
        }, {
            Symbol: d
        })
    }
}, function(e, t, n) {
    n(135)("iterator")
}, function(e, t, n) {
    "use strict";
    var r, o = "object" == typeof Reflect ? Reflect : null,
        i = o && "function" == typeof o.apply ? o.apply : function(e, t, n) {
            return Function.prototype.apply.call(e, t, n)
        };
    r = o && "function" == typeof o.ownKeys ? o.ownKeys : Object.getOwnPropertySymbols ? function(e) {
        return Object.getOwnPropertyNames(e).concat(Object.getOwnPropertySymbols(e))
    } : function(e) {
        return Object.getOwnPropertyNames(e)
    };
    var a = Number.isNaN || function(e) {
        return e != e
    };

    function c() {
        c.init.call(this)
    }
    e.exports = c, c.EventEmitter = c, c.prototype._events = void 0, c.prototype._eventsCount = 0, c.prototype._maxListeners = void 0;
    var s = 10;

    function u(e) {
        if ("function" != typeof e) throw new TypeError('The "listener" argument must be of type Function. Received type ' + typeof e)
    }

    function f(e) {
        return void 0 === e._maxListeners ? c.defaultMaxListeners : e._maxListeners
    }

    function l(e, t, n, r) {
        var o, i, a, c;
        if (u(n), void 0 === (i = e._events) ? (i = e._events = Object.create(null), e._eventsCount = 0) : (void 0 !== i.newListener && (e.emit("newListener", t, n.listener ? n.listener : n), i = e._events), a = i[t]), void 0 === a) a = i[t] = n, ++e._eventsCount;
        else if ("function" == typeof a ? a = i[t] = r ? [n, a] : [a, n] : r ? a.unshift(n) : a.push(n), (o = f(e)) > 0 && a.length > o && !a.warned) {
            a.warned = !0;
            var s = new Error("Possible EventEmitter memory leak detected. " + a.length + " " + String(t) + " listeners added. Use emitter.setMaxListeners() to increase limit");
            s.name = "MaxListenersExceededWarning", s.emitter = e, s.type = t, s.count = a.length, c = s, console && console.warn && console.warn(c)
        }
        return e
    }

    function d() {
        if (!this.fired) return this.target.removeListener(this.type, this.wrapFn), this.fired = !0, 0 === arguments.length ? this.listener.call(this.target) : this.listener.apply(this.target, arguments)
    }

    function p(e, t, n) {
        var r = {
                fired: !1,
                wrapFn: void 0,
                target: e,
                type: t,
                listener: n
            },
            o = d.bind(r);
        return o.listener = n, r.wrapFn = o, o
    }

    function h(e, t, n) {
        var r = e._events;
        if (void 0 === r) return [];
        var o = r[t];
        return void 0 === o ? [] : "function" == typeof o ? n ? [o.listener || o] : [o] : n ? function(e) {
            for (var t = new Array(e.length), n = 0; n < t.length; ++n) t[n] = e[n].listener || e[n];
            return t
        }(o) : y(o, o.length)
    }

    function v(e) {
        var t = this._events;
        if (void 0 !== t) {
            var n = t[e];
            if ("function" == typeof n) return 1;
            if (void 0 !== n) return n.length
        }
        return 0
    }

    function y(e, t) {
        for (var n = new Array(t), r = 0; r < t; ++r) n[r] = e[r];
        return n
    }
    Object.defineProperty(c, "defaultMaxListeners", {
        enumerable: !0,
        get: function() {
            return s
        },
        set: function(e) {
            if ("number" != typeof e || e < 0 || a(e)) throw new RangeError('The value of "defaultMaxListeners" is out of range. It must be a non-negative number. Received ' + e + ".");
            s = e
        }
    }), c.init = function() {
        void 0 !== this._events && this._events !== Object.getPrototypeOf(this)._events || (this._events = Object.create(null), this._eventsCount = 0), this._maxListeners = this._maxListeners || void 0
    }, c.prototype.setMaxListeners = function(e) {
        if ("number" != typeof e || e < 0 || a(e)) throw new RangeError('The value of "n" is out of range. It must be a non-negative number. Received ' + e + ".");
        return this._maxListeners = e, this
    }, c.prototype.getMaxListeners = function() {
        return f(this)
    }, c.prototype.emit = function(e) {
        for (var t = [], n = 1; n < arguments.length; n++) t.push(arguments[n]);
        var r = "error" === e,
            o = this._events;
        if (void 0 !== o) r = r && void 0 === o.error;
        else if (!r) return !1;
        if (r) {
            var a;
            if (t.length > 0 && (a = t[0]), a instanceof Error) throw a;
            var c = new Error("Unhandled error." + (a ? " (" + a.message + ")" : ""));
            throw c.context = a, c
        }
        var s = o[e];
        if (void 0 === s) return !1;
        if ("function" == typeof s) i(s, this, t);
        else {
            var u = s.length,
                f = y(s, u);
            for (n = 0; n < u; ++n) i(f[n], this, t)
        }
        return !0
    }, c.prototype.addListener = function(e, t) {
        return l(this, e, t, !1)
    }, c.prototype.on = c.prototype.addListener, c.prototype.prependListener = function(e, t) {
        return l(this, e, t, !0)
    }, c.prototype.once = function(e, t) {
        return u(t), this.on(e, p(this, e, t)), this
    }, c.prototype.prependOnceListener = function(e, t) {
        return u(t), this.prependListener(e, p(this, e, t)), this
    }, c.prototype.removeListener = function(e, t) {
        var n, r, o, i, a;
        if (u(t), void 0 === (r = this._events)) return this;
        if (void 0 === (n = r[e])) return this;
        if (n === t || n.listener === t) 0 == --this._eventsCount ? this._events = Object.create(null) : (delete r[e], r.removeListener && this.emit("removeListener", e, n.listener || t));
        else if ("function" != typeof n) {
            for (o = -1, i = n.length - 1; i >= 0; i--)
                if (n[i] === t || n[i].listener === t) {
                    a = n[i].listener, o = i;
                    break
                } if (o < 0) return this;
            0 === o ? n.shift() : function(e, t) {
                for (; t + 1 < e.length; t++) e[t] = e[t + 1];
                e.pop()
            }(n, o), 1 === n.length && (r[e] = n[0]), void 0 !== r.removeListener && this.emit("removeListener", e, a || t)
        }
        return this
    }, c.prototype.off = c.prototype.removeListener, c.prototype.removeAllListeners = function(e) {
        var t, n, r;
        if (void 0 === (n = this._events)) return this;
        if (void 0 === n.removeListener) return 0 === arguments.length ? (this._events = Object.create(null), this._eventsCount = 0) : void 0 !== n[e] && (0 == --this._eventsCount ? this._events = Object.create(null) : delete n[e]), this;
        if (0 === arguments.length) {
            var o, i = Object.keys(n);
            for (r = 0; r < i.length; ++r) "removeListener" !== (o = i[r]) && this.removeAllListeners(o);
            return this.removeAllListeners("removeListener"), this._events = Object.create(null), this._eventsCount = 0, this
        }
        if ("function" == typeof(t = n[e])) this.removeListener(e, t);
        else if (void 0 !== t)
            for (r = t.length - 1; r >= 0; r--) this.removeListener(e, t[r]);
        return this
    }, c.prototype.listeners = function(e) {
        return h(this, e, !0)
    }, c.prototype.rawListeners = function(e) {
        return h(this, e, !1)
    }, c.listenerCount = function(e, t) {
        return "function" == typeof e.listenerCount ? e.listenerCount(t) : v.call(e, t)
    }, c.prototype.listenerCount = v, c.prototype.eventNames = function() {
        return this._eventsCount > 0 ? r(this._events) : []
    }
}, function(e, t, n) {
    "use strict";
    n(14), n(23), n(24), n(32), n(87), n(20), n(51), n(5), n(6), n(52), n(79), n(8), n(31), n(12);
    var r = n(7);

    function o(e) {
        return function(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                return n
            }
        }(e) || function(e) {
            if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
        }(e) || function() {
            throw new TypeError("Invalid attempt to spread non-iterable instance")
        }()
    }

    function i(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), i(this, "selectors", []), i(this, "setSelectors", (function(e) {
            t.selectors = e
        })), i(this, "isNodeRedacted", (function(e) {
            return "OBJECT" === e.tagName || !!t.selectors.find((function(t) {
                return e.matches && e.matches(t)
            }))
        })), i(this, "isRedacted", (function(e) {
            return !!e && !![e].concat(o(Object(r.parents)(e))).find((function(e) {
                return t.isNodeRedacted(e)
            }))
        })), i(this, "redactedNodes", (function() {
            var e = new Set;
            return t.selectors.forEach((function(t) {
                return document.querySelectorAll(t).forEach((function(t) {
                    return e.add(t)
                }))
            })), o(e)
        }))
    }
}, function(e, t, n) {
    var r = n(9),
        o = n(16),
        i = n(47);
    e.exports = r ? function(e, t, n) {
        return o.f(e, t, i(1, n))
    } : function(e, t, n) {
        return e[t] = n, e
    }
}, function(e, t, n) {
    var r = n(63),
        o = n(54);
    e.exports = function(e) {
        return r(o(e))
    }
}, function(e, t, n) {
    var r = n(3),
        o = n(27),
        i = n(11),
        a = n(89),
        c = n(113),
        s = n(35),
        u = s.get,
        f = s.enforce,
        l = String(String).split("String");
    (e.exports = function(e, t, n, c) {
        var s = !!c && !!c.unsafe,
            u = !!c && !!c.enumerable,
            d = !!c && !!c.noTargetGet;
        "function" == typeof n && ("string" != typeof t || i(n, "name") || o(n, "name", t), f(n).source = l.join("string" == typeof t ? t : "")), e !== r ? (s ? !d && e[t] && (u = !0) : delete e[t], u ? e[t] = n : o(e, t, n)) : u ? e[t] = n : a(t, n)
    })(Function.prototype, "toString", (function() {
        return "function" == typeof this && u(this).source || c(this)
    }))
}, function(e, t, n) {
    var r = n(0),
        o = n(1),
        i = n(28),
        a = n(41).f,
        c = n(9),
        s = o((function() {
            a(1)
        }));
    r({
        target: "Object",
        stat: !0,
        forced: !c || s,
        sham: !c
    }, {
        getOwnPropertyDescriptor: function(e, t) {
            return a(i(e), t)
        }
    })
}, function(e, t, n) {
    var r = n(3),
        o = n(132),
        i = n(121),
        a = n(27);
    for (var c in o) {
        var s = r[c],
            u = s && s.prototype;
        if (u && u.forEach !== i) try {
            a(u, "forEach", i)
        } catch (e) {
            u.forEach = i
        }
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(1),
        i = n(70),
        a = n(10),
        c = n(22),
        s = n(13),
        u = n(86),
        f = n(122),
        l = n(85),
        d = n(4),
        p = n(136),
        h = d("isConcatSpreadable"),
        v = p >= 51 || !o((function() {
            var e = [];
            return e[h] = !1, e.concat()[0] !== e
        })),
        y = l("concat"),
        g = function(e) {
            if (!a(e)) return !1;
            var t = e[h];
            return void 0 !== t ? !!t : i(e)
        };
    r({
        target: "Array",
        proto: !0,
        forced: !v || !y
    }, {
        concat: function(e) {
            var t, n, r, o, i, a = c(this),
                l = f(a, 0),
                d = 0;
            for (t = -1, r = arguments.length; t < r; t++)
                if (i = -1 === t ? a : arguments[t], g(i)) {
                    if (d + (o = s(i.length)) > 9007199254740991) throw TypeError("Maximum allowed index exceeded");
                    for (n = 0; n < o; n++, d++) n in i && u(l, d, i[n])
                } else {
                    if (d >= 9007199254740991) throw TypeError("Maximum allowed index exceeded");
                    u(l, d++, i)
                } return l.length = d, l
        }
    })
}, function(e, t, n) {
    "use strict";
    n(14), n(36), n(20), n(30), n(43), n(44), n(31);
    var r = n(62),
        o = n.n(r);

    function i(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function a(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), a(this, "_mousePosition", {
            x: -1,
            y: -1
        }), a(this, "_mouseIsDown", !1), a(this, "_mouseIsIn", !1), a(this, "track", (function(e) {
            e.addEventListener("mousedown", t._onMouseDown, {
                capture: !0,
                passive: !0
            }), e.addEventListener("mouseup", t._onMouseUp, {
                capture: !0,
                passive: !0
            }), e.addEventListener("mouseenter", t._onMouseEnter, {
                capture: !0,
                passive: !0
            }), e.addEventListener("mouseleave", t._onMouseLeave, {
                capture: !0,
                passive: !0
            }), e.addEventListener("mousemove", t._onMouseMove, {
                capture: !0,
                passive: !0
            })
        })), a(this, "untrack", (function(e) {
            e.removeEventListener("mousedown", t._onMouseDown, {
                capture: !0,
                passive: !0
            }), e.removeEventListener("mouseup", t._onMouseUp, {
                capture: !0,
                passive: !0
            }), e.removeEventListener("mouseenter", t._onMouseEnter, {
                capture: !0,
                passive: !0
            }), e.removeEventListener("mouseleave", t._onMouseLeave, {
                capture: !0,
                passive: !0
            }), e.removeEventListener("mousemove", t._onMouseMove, {
                capture: !0,
                passive: !0
            })
        })), a(this, "isDown", (function() {
            return t._mouseIsDown
        })), a(this, "setDown", (function(e) {
            t._mouseIsDown = e, t.onChange && t.onChange(t.state())
        })), a(this, "isIn", (function() {
            return t._mouseIsIn > 0
        })), a(this, "position", (function() {
            return t._mousePosition
        })), a(this, "setPosition", (function(e, n, r) {
            if (r) {
                var o = r.getBoundingClientRect();
                e += Math.round(o.left), n += Math.round(o.top)
            }
            t._mousePosition = {
                x: e,
                y: n
            }, t.onChange && t.onChange(t.state())
        })), a(this, "state", (function() {
            return function(e) {
                for (var t = 1; t < arguments.length; t++) {
                    var n = null != arguments[t] ? arguments[t] : {};
                    t % 2 ? i(Object(n), !0).forEach((function(t) {
                        a(e, t, n[t])
                    })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : i(Object(n)).forEach((function(t) {
                        Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
                    }))
                }
                return e
            }({}, t.position(), {
                down: t.isDown(),
                in: t.isIn()
            })
        })), a(this, "_onMouseDown", (function(e) {
            e.isTrusted && t.setDown(!0)
        })), a(this, "_onMouseUp", (function(e) {
            e.isTrusted && t.setDown(!1)
        })), a(this, "_onMouseEnter", (function(e) {
            e.isTrusted && (t._mouseIsIn += 1)
        })), a(this, "_onMouseLeave", (function(e) {
            e.isTrusted && (t._mouseIsIn -= 1)
        })), a(this, "_onMouseMove", o()((function(e) {
            if (e.isTrusted) {
                var n = e.clientX,
                    r = e.clientY,
                    o = e.view && e.view.frameElement;
                t.setPosition(n, r, o)
            }
        }), 100))
    }
}, function(e, t, n) {
    "use strict";
    n(14), n(23), n(24), n(32), n(36), n(51), n(147), n(193), n(5), n(6), n(52), n(148), n(8), n(12);
    var r = n(7),
        o = n(26);

    function i(e) {
        return function(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                return n
            }
        }(e) || function(e) {
            if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
        }(e) || function() {
            throw new TypeError("Invalid attempt to spread non-iterable instance")
        }()
    }

    function a(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), a(this, "isIgnored", (function(e) {
            var t = e.getAttribute && e.getAttribute("class");
            if (t && t.indexOf && -1 !== t.indexOf("__cbio_ignored")) return !0;
            if ([window.Node.COMMENT_NODE, window.Node.CDATA_SECTION_NODE, window.Node.ENTITY_REFERENCE_NODE, window.Node.ENTITY_NODE, window.Node.PROCESSING_INSTRUCTION_NODE, window.Node.NOTATION_NODE, window.Node.DOCUMENT_TYPE_NODE].includes(e.nodeType)) return !0;
            if ("SCRIPT" === e.tagName) return !0;
            var n = e.parentNode || e.defaultView && e.defaultView.frameElement;
            return !(!n || "STYLE" !== n.tagName) || !!n && o.a.isNodeRedacted(n)
        })), a(this, "children", (function(e) {
            return "IFRAME" === e.tagName && Object(r.isxdoc)(e) ? e.__vdom ? [e.__vdom.document] : [] : "IFRAME" !== e.tagName || Object(r.isxdoc)(e) ? e.shadowRoot ? [e.shadowRoot].concat(i(Array.from(e.childNodes))).filter((function(e) {
                return !t.isIgnored(e)
            })) : Array.from(e.childNodes).filter((function(e) {
                return !t.isIgnored(e)
            })) : [e.contentWindow.document].filter((function(e) {
                return !t.isIgnored(e)
            }))
        })), a(this, "depthFirst", (function(e, n) {
            return Object(r.depthFirst)(e, (function(e, r) {
                return r(t.children(e)), n(e, r)
            }))
        })), a(this, "find", (function(e, n) {
            if (!e) return null;
            var r = null;
            return t.depthFirst(e, (function(e, t) {
                r ? t() : n(e) && (r = e)
            })), r
        }))
    }
}, function(e, t, n) {
    var r, o, i, a = n(115),
        c = n(3),
        s = n(10),
        u = n(27),
        f = n(11),
        l = n(83),
        d = n(67),
        p = c.WeakMap;
    if (a) {
        var h = new p,
            v = h.get,
            y = h.has,
            g = h.set;
        r = function(e, t) {
            return g.call(h, e, t), t
        }, o = function(e) {
            return v.call(h, e) || {}
        }, i = function(e) {
            return y.call(h, e)
        }
    } else {
        var m = l("state");
        d[m] = !0, r = function(e, t) {
            return u(e, m, t), t
        }, o = function(e) {
            return f(e, m) ? e[m] : {}
        }, i = function(e) {
            return f(e, m)
        }
    }
    e.exports = {
        set: r,
        get: o,
        has: i,
        enforce: function(e) {
            return i(e) ? o(e) : r(e, {})
        },
        getterFor: function(e) {
            return function(t) {
                var n;
                if (!s(t) || (n = o(t)).type !== e) throw TypeError("Incompatible receiver, " + e + " required");
                return n
            }
        }
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(21).filter,
        i = n(85),
        a = n(39),
        c = i("filter"),
        s = a("filter");
    r({
        target: "Array",
        proto: !0,
        forced: !c || !s
    }, {
        filter: function(e) {
            return o(this, e, arguments.length > 1 ? arguments[1] : void 0)
        }
    })
}, function(e, t, n) {
    "use strict";
    (function(r) {
        function o(e) {
            return (o = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
                return typeof e
            } : function(e) {
                return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
            })(e)
        }
        t.log = function() {
            var e;
            return "object" === ("undefined" == typeof console ? "undefined" : o(console)) && console.log && (e = console).log.apply(e, arguments)
        }, t.formatArgs = function(t) {
            if (t[0] = (this.useColors ? "%c" : "") + this.namespace + (this.useColors ? " %c" : " ") + t[0] + (this.useColors ? "%c " : " ") + "+" + e.exports.humanize(this.diff), !this.useColors) return;
            var n = "color: " + this.color;
            t.splice(1, 0, n, "color: inherit");
            var r = 0,
                o = 0;
            t[0].replace(/%[a-zA-Z%]/g, (function(e) {
                "%%" !== e && (r++, "%c" === e && (o = r))
            })), t.splice(o, 0, n)
        }, t.save = function(e) {
            try {
                e ? t.storage.setItem("debug", e) : t.storage.removeItem("debug")
            } catch (e) {}
        }, t.load = function() {
            var e;
            try {
                e = t.storage.getItem("debug")
            } catch (e) {}!e && void 0 !== r && "env" in r && (e = r.env.DEBUG);
            return e
        }, t.useColors = function() {
            if ("undefined" != typeof window && window.process && ("renderer" === window.process.type || window.process.__nwjs)) return !0;
            if ("undefined" != typeof navigator && navigator.userAgent && navigator.userAgent.toLowerCase().match(/(edge|trident)\/(\d+)/)) return !1;
            return "undefined" != typeof document && document.documentElement && document.documentElement.style && document.documentElement.style.WebkitAppearance || "undefined" != typeof window && window.console && (window.console.firebug || window.console.exception && window.console.table) || "undefined" != typeof navigator && navigator.userAgent && navigator.userAgent.toLowerCase().match(/firefox\/(\d+)/) && parseInt(RegExp.$1, 10) >= 31 || "undefined" != typeof navigator && navigator.userAgent && navigator.userAgent.toLowerCase().match(/applewebkit\/(\d+)/)
        }, t.storage = function() {
            try {
                return localStorage
            } catch (e) {}
        }(), t.colors = ["#0000CC", "#0000FF", "#0033CC", "#0033FF", "#0066CC", "#0066FF", "#0099CC", "#0099FF", "#00CC00", "#00CC33", "#00CC66", "#00CC99", "#00CCCC", "#00CCFF", "#3300CC", "#3300FF", "#3333CC", "#3333FF", "#3366CC", "#3366FF", "#3399CC", "#3399FF", "#33CC00", "#33CC33", "#33CC66", "#33CC99", "#33CCCC", "#33CCFF", "#6600CC", "#6600FF", "#6633CC", "#6633FF", "#66CC00", "#66CC33", "#9900CC", "#9900FF", "#9933CC", "#9933FF", "#99CC00", "#99CC33", "#CC0000", "#CC0033", "#CC0066", "#CC0099", "#CC00CC", "#CC00FF", "#CC3300", "#CC3333", "#CC3366", "#CC3399", "#CC33CC", "#CC33FF", "#CC6600", "#CC6633", "#CC9900", "#CC9933", "#CCCC00", "#CCCC33", "#FF0000", "#FF0033", "#FF0066", "#FF0099", "#FF00CC", "#FF00FF", "#FF3300", "#FF3333", "#FF3366", "#FF3399", "#FF33CC", "#FF33FF", "#FF6600", "#FF6633", "#FF9900", "#FF9933", "#FFCC00", "#FFCC33"], e.exports = n(191)(t), e.exports.formatters.j = function(e) {
            try {
                return JSON.stringify(e)
            } catch (e) {
                return "[UnexpectedJSONParseError]: " + e.message
            }
        }
    }).call(this, n(190))
}, function(e, t, n) {
    "use strict";
    n(14), n(23), n(24), n(32), n(36), n(87), n(20), n(51), n(5), n(78), n(106), n(146), n(30), n(43), n(44), n(6), n(52), n(8), n(31), n(12);
    var r = n(7),
        o = n(15),
        i = n(46),
        a = n(34),
        c = n(26);

    function s(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    var u = function() {
        function e() {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e)
        }
        var t, n, r;
        return t = e, r = [{
            key: "fromImage",
            value: function(e) {
                var t = document.createElement("canvas");
                return t.width = e.width, t.height = e.height, t.getContext("2d").drawImage(e, 0, 0, t.width, t.height), this.fromCanvas(t)
            }
        }, {
            key: "fromCanvas",
            value: function(e) {
                return e.toDataURL ? e.toDataURL("image/webp", .3) : null
            }
        }], (n = null) && s(t.prototype, n), r && s(t, r), e
    }();

    function f(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function l(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? f(Object(n), !0).forEach((function(t) {
                p(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : f(Object(n)).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function d(e) {
        return function(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                return n
            }
        }(e) || function(e) {
            if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
        }(e) || function() {
            throw new TypeError("Invalid attempt to spread non-iterable instance")
        }()
    }

    function p(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), p(this, "isLocalResource", (function(e) {
            return !!/^https?:\/\/localhost/.test(e) || (!!/^https?:\/\/127.0.0.1/.test(e) || !!/^file:\/\//.test(e))
        })), p(this, "_serializeAttributes", (function(e, t) {
            if (e.attributes) {
                var n = {};
                Array.from(e.attributes || []).forEach((function(t) {
                    "href" === t.nodeName || "src" === t.nodeName ? n[t.nodeName] = e[t.nodeName] : n[t.nodeName] = t.nodeValue
                })), t.attributes = n
            }
        })), p(this, "_serializeDocument", (function(e, t) {
            e.nodeType === window.Node.DOCUMENT_NODE && (t.url = e.location && e.location.href, t.size = {
                width: (e.documentElement || e).clientWidth,
                height: (e.documentElement || e).clientHeight
            }, t.focus = o.a.get(e.activeElement))
        })), p(this, "_serializeInput", (function(e, t) {
            "INPUT" !== e.tagName && "TEXTAREA" !== e.tagName && "OPTION" !== e.tagName || (t.value = e.value, void 0 !== e.checked && (t.checked = e.checked))
        })), p(this, "_serializeOption", (function(e, t) {
            "OPTION" === e.tagName && (t.selected = e.selected)
        })), p(this, "_serializeCSSSheet", (function(e) {
            try {
                if (e.media && e.media.length && !window.matchMedia(e.media).matches) return ""
            } catch (t) {
                console.warn("CobrowseIO: Media detection for sheet failed:", t, "sheet", e)
            }
            return [].slice.call(e.cssRules).reduce((function(e, t) {
                try {
                    return e + t.cssText
                } catch (t) {
                    return e
                }
            }), "")
        })), p(this, "_serializeCSS", (function(e, n) {
            try {
                if ("STYLE" === e.tagName || "LINK" === e.tagName && "stylesheet" === e.rel && e.sheet && t.isLocalResource(e.href)) n.css = t._serializeCSSSheet(e.sheet);
                else if (e.nodeType === window.Node.DOCUMENT_FRAGMENT_NODE) {
                    var r = [];
                    d(e.adoptedStyleSheets || []).forEach((function(e) {
                        r = r.concat([].slice.call(e.cssRules))
                    })), n.css = t._serializeCSSSheet({
                        cssRules: r
                    })
                }
            } catch (t) {
                console.warn("CobrowseIO: CSS serialize failed:", t.message, "for", e)
            }
        })), p(this, "_serializeCanvas", (function(e, t) {
            "CANVAS" !== e.tagName || c.a.isRedacted(e) || (t.data = u.fromCanvas(e))
        })), p(this, "_serializeImage", (function(e, n) {
            if ("IMG" === e.tagName && !c.a.isRedacted(e) && t.isLocalResource(e.src)) try {
                n.attributes.src = u.fromImage(e)
            } catch (t) {
                console.warn("CobrowseIO: Failed to serialize image", e)
            }
        })), p(this, "_serializeScroll", (function(e, t) {
            Object(r.isxdoc)(e) || (e.nodeType === window.Node.DOCUMENT_NODE ? t.scroll = {
                x: e.defaultView.pageXOffset || 0,
                y: e.defaultView.pageYOffset || 0
            } : (e.scrollWidth > e.clientWidth || e.scrollHeight > e.clientHeight) && (t.scroll = {
                x: e.scrollLeft || 0,
                y: e.scrollTop || 0
            }))
        })), p(this, "_serializeRedaction", (function(e, t) {
            if (c.a.isRedacted(e)) {
                delete t.value, t.attributes = {
                    class: t.attributes.class,
                    style: t.attributes.style
                };
                var n = window.getComputedStyle(e);
                t.redaction = {
                    height: n.height,
                    width: n.width
                }
            }
        })), p(this, "_serialize", (function(e) {
            if (!o.a.get(e)) throw console.error("node missing id", e, e.parentNode), new Error("node missing id", e);
            if (!i.a.isDOMNode(e)) return e;
            if (!e.nodeType) throw new Error("node missing type", e);
            var n = {
                id: o.a.get(e),
                nodeType: e.nodeType
            };
            return t._serializeAttributes(e, n), t._serializeImage(e, n), t._serializeDocument(e, n), t._serializeScroll(e, n), t._serializeInput(e, n), t._serializeOption(e, n), t._serializeCSS(e, n), t._serializeCanvas(e, n), t._serializeRedaction(e, n), e.tagName && (n.tagName = e.tagName), e.nodeValue && (n.content = e.nodeValue), n
        })), p(this, "serialize", (function(e) {
            return [e].concat(d(Object(r.parents)(e))).find((function(e) {
                return a.a.isIgnored(e)
            })) ? null : l({}, t._serialize(e), {
                childNodes: a.a.children(e).map((function(e) {
                    return i.a.isDOMNode(e) ? {
                        id: o.a.get(e)
                    } : l({}, e)
                }))
            })
        }))
    }
}, function(e, t, n) {
    var r = n(9),
        o = n(1),
        i = n(11),
        a = Object.defineProperty,
        c = {},
        s = function(e) {
            throw e
        };
    e.exports = function(e, t) {
        if (i(c, e)) return c[e];
        t || (t = {});
        var n = [][e],
            u = !!i(t, "ACCESSORS") && t.ACCESSORS,
            f = i(t, 0) ? t[0] : s,
            l = i(t, 1) ? t[1] : void 0;
        return c[e] = !!n && !o((function() {
            if (u && !r) return !0;
            var e = {
                length: -1
            };
            u ? a(e, 1, {
                enumerable: !0,
                get: s
            }) : e[1] = 1, n.call(e, f, l)
        }))
    }
}, function(e, t, n) {
    var r = n(0),
        o = n(1),
        i = n(22),
        a = n(60),
        c = n(128);
    r({
        target: "Object",
        stat: !0,
        forced: o((function() {
            a(1)
        })),
        sham: !c
    }, {
        getPrototypeOf: function(e) {
            return a(i(e))
        }
    })
}, function(e, t, n) {
    var r = n(9),
        o = n(82),
        i = n(47),
        a = n(28),
        c = n(65),
        s = n(11),
        u = n(111),
        f = Object.getOwnPropertyDescriptor;
    t.f = r ? f : function(e, t) {
        if (e = a(e), t = c(t, !0), u) try {
            return f(e, t)
        } catch (e) {}
        if (s(e, t)) return i(!o.f.call(e, t), e[t])
    }
}, function(e, t) {
    var n = Math.ceil,
        r = Math.floor;
    e.exports = function(e) {
        return isNaN(e = +e) ? 0 : (e > 0 ? r : n)(e)
    }
}, function(e, t, n) {
    var r = n(0),
        o = n(9),
        i = n(117),
        a = n(28),
        c = n(41),
        s = n(86);
    r({
        target: "Object",
        stat: !0,
        sham: !o
    }, {
        getOwnPropertyDescriptors: function(e) {
            for (var t, n, r = a(e), o = c.f, u = i(r), f = {}, l = 0; u.length > l;) void 0 !== (n = o(r, t = u[l++])) && s(f, t, n);
            return f
        }
    })
}, function(e, t, n) {
    var r = n(0),
        o = n(22),
        i = n(73);
    r({
        target: "Object",
        stat: !0,
        forced: n(1)((function() {
            i(1)
        }))
    }, {
        keys: function(e) {
            return i(o(e))
        }
    })
}, function(e, t, n) {
    "use strict";
    n.r(t);
    n(20), n(6), n(96);
    var r = n(62),
        o = n.n(r),
        i = n(7),
        a = (n(14), n(23), n(24), n(36), n(5), n(30), n(43), n(40), n(44), n(52), n(8), n(31), n(12), n(25));

    function c(e) {
        return (c = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function s(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function u(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? s(Object(n), !0).forEach((function(t) {
                f(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : s(Object(n)).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function f(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }

    function l(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function d(e, t) {
        return !t || "object" !== c(t) && "function" != typeof t ? function(e) {
            if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return e
        }(e) : t
    }

    function p(e) {
        return (p = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function h(e, t) {
        return (h = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }
    var v = function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), (e = d(this, p(t).call(this)))._resource = {}, e._headers = {}, e
            }
            var n, r, o;
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && h(e, t)
            }(t, e), n = t, r = [{
                key: "url",
                value: function() {
                    throw new Error("url() must be implemented")
                }
            }, {
                key: "id",
                value: function() {
                    return this.field("id")
                }
            }, {
                key: "field",
                value: function(e) {
                    return (this._resource || {})[e]
                }
            }, {
                key: "errors",
                value: function(e) {
                    return e.status >= 400 ? e.json().then((function(e) {
                        throw new Error(e.message)
                    })) : e
                }
            }, {
                key: "cache",
                value: function(e) {
                    this._resource = u({}, this._resource, {}, e)
                }
            }, {
                key: "fetch",
                value: function(e) {
                    function t() {
                        return e.apply(this, arguments)
                    }
                    return t.toString = function() {
                        return e.toString()
                    }, t
                }((function() {
                    var e = this;
                    return fetch(this.url(), {
                        headers: this.headers()
                    }).then(this.errors).then((function(e) {
                        return e.json()
                    })).then((function(t) {
                        return e.updateResource(t)
                    }))
                }))
            }, {
                key: "update",
                value: function(e) {
                    var t = this;
                    return fetch(this.url(), {
                        method: "PUT",
                        headers: this.headers(),
                        body: JSON.stringify(e)
                    }).then(this.errors).then((function(e) {
                        return e.json()
                    })).then((function(e) {
                        return t.updateResource(e)
                    }))
                }
            }, {
                key: "destroy",
                value: function() {
                    var e = this;
                    return fetch(this.url(), {
                        method: "DELETE",
                        headers: this.headers()
                    }).then(this.errors).then((function() {
                        return e.updateResource({})
                    }))
                }
            }, {
                key: "updateResource",
                value: function(e) {
                    return this._resource = e, this.emit("updated", this), this
                }
            }, {
                key: "headers",
                value: function() {
                    return u({}, t.headers(), {}, this._headers)
                }
            }, {
                key: "setHeader",
                value: function(e, t) {
                    this._headers[e] = t
                }
            }, {
                key: "resource",
                get: function() {
                    return this._resource
                }
            }], o = [{
                key: "create",
                value: function() {
                    var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
                        t = (new this).updateResource(e);
                    return fetch(t.url(), {
                        method: t.id() ? "PUT" : "POST",
                        headers: this.headers(),
                        body: JSON.stringify(e)
                    }).then(this.errors).then((function(e) {
                        return e.json()
                    })).then((function(e) {
                        return t.updateResource(e)
                    }))
                }
            }, {
                key: "headers",
                value: function() {
                    return this._globalHeaders
                }
            }, {
                key: "setHeader",
                value: function(e, t) {
                    this._globalHeaders || (this._globalHeaders = {}), this._globalHeaders[e] = t
                }
            }, {
                key: "getHeader",
                value: function(e) {
                    return this._globalHeaders && this._globalHeaders[e]
                }
            }, {
                key: "api",
                set: function(e) {
                    this._api = e
                },
                get: function() {
                    return this._api
                }
            }], r && l(n.prototype, r), o && l(n, o), t
        }(a.EventEmitter),
        y = (n(32), n(87), n(144), n(242)),
        g = n(18),
        m = (n(184), n(145), n(186), function(e, t) {
            var r = n(45).default,
                o = ["X-CobrowseSDKVersion=".concat(r.version), "X-CobrowsePlatform=web", "X-CobrowseDevice=".concat(r.deviceId()), "X-CobrowseLicense=".concat(r.license)].join("&"),
                i = e.replace("https://", "wss://").replace("http://", "ws://");
            return "".concat(i, "/sockets/1/ws?access_token=").concat(t, "&").concat(o)
        }),
        b = n(109),
        w = n.n(b);

    function _(e) {
        return (_ = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function O(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function E(e) {
        return (E = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function S(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function x(e, t) {
        return (x = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function C(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var j = n(37)("cbio.CBORSocket"),
        A = function(e) {
            function t(e) {
                var n;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), n = function(e, t) {
                    return !t || "object" !== _(t) && "function" != typeof t ? S(e) : t
                }(this, E(t).call(this)), C(S(n), "_createSocket", (function(e) {
                    j("creating ws");
                    var t = new WebSocket(e);
                    return t.binaryType = "arraybuffer", t.addEventListener("open", n._handleOpen), t.addEventListener("message", n._handleMessage), t.addEventListener("close", n._handleClose), t.addEventListener("error", n._handleError), t
                })), C(S(n), "_handleOpen", (function() {
                    j("ws opened"), n.emit("open"), clearTimeout(n._openSuccessTimeout), n._openSuccessTimeout = setTimeout((function() {
                        j("counting open as success"), n._attempts = 0
                    }), 5e3)
                })), C(S(n), "_handleMessage", (function(e) {
                    try {
                        var t = w.a.decode(e.data),
                            r = t.event,
                            o = t.data;
                        return r ? n.emit(r, o) : console.error("Socket received message without event", e)
                    } catch (t) {
                        return console.error("Error processing message", e.data, t.stack)
                    }
                })), C(S(n), "_reconnectDelay", (function(e) {
                    var t = 1 + .5 * Math.random(),
                        r = Math.floor(Math.pow(300 * e, 1.2) * t);
                    return Math.min(Math.max(n._minDelay, r), n._maxDelay)
                })), C(S(n), "_reconnect", (function() {
                    if (n._socket)
                        if (n._reconnectTimeout) j("reconnect skipped as reconnect already scheduled");
                        else {
                            if (!1 === navigator.onLine) return j("navigator offline"), void(n._reconnectTimeout = setTimeout((function() {
                                n._reconnectTimeout = null, n._reconnect()
                            }), 1e3));
                            var e = n._reconnectDelay(n._attempts);
                            j("reconnecting in", e, "attempts", n._attempts), n._reconnectTimeout = setTimeout((function() {
                                n._reconnectTimeout = null, n._socket ? n._socket = n._createSocket(n._urlFn()) : console.error("Socket tried to reconnect after close")
                            }), e), n._attempts += 1
                        }
                    else j("reconnect skipped as socket exists")
                })), C(S(n), "_handleClose", (function() {
                    j("ws closed"), n.emit("close"), n._reconnect(), clearTimeout(n._openSuccessTimeout)
                })), C(S(n), "_handleError", (function(e) {
                    n.emit("error", e)
                })), C(S(n), "_sendPing", (function() {
                    n.send("ping")
                })), C(S(n), "setMaxReconnectDelay", (function(e) {
                    j("set max delay", e), n._maxDelay = e
                })), C(S(n), "setMinReconnectDelay", (function(e) {
                    j("set min delay", e), n._minDelay = e
                })), C(S(n), "setUrl", (function(e) {
                    n._urlFn = e
                })), C(S(n), "send", (function(e, t) {
                    n.connected && n._socket.send(w.a.encode(t ? {
                        event: e,
                        data: t
                    } : {
                        event: e
                    }))
                })), C(S(n), "close", (function() {
                    if (j("close"), n._socket) try {
                        var e;
                        (e = n._socket).close.apply(e, arguments)
                    } catch (e) {
                        j("error closing socket", e)
                    }
                    n._socket = null, n._urlFn = null, n.removeAllListeners(), clearTimeout(n._reconnectTimeout), clearInterval(n._pingInterval), window.removeEventListener("unload", n.close)
                })), j("created cbor socket"), n._urlFn = e, n._socket = n._createSocket(e()), n._attempts = 0, n._maxDelay = 3e4, n._minDelay = 1 + Math.floor(1e3 * Math.random()), n._pingInterval = setInterval(n._sendPing, 6e4), window.addEventListener("unload", n.close), n.on("error", (function(e) {
                    return j("ws errored", e)
                })), n
            }
            var n, r, o;
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && x(e, t)
            }(t, e), n = t, (r = [{
                key: "bufferedAmount",
                get: function() {
                    return this._socket ? this._socket.bufferedAmount : 1 / 0
                }
            }, {
                key: "connected",
                get: function() {
                    return !!this._socket && this._socket.readyState === this._socket.OPEN
                }
            }]) && O(n.prototype, r), o && O(n, o), t
        }(a.EventEmitter);

    function k(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    var T = function() {
        function e(t, n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this.rate = t, this.period = n, this._tracked = []
        }
        var t, n, r;
        return t = e, (n = [{
            key: "limit",
            value: function() {
                var e = Date.now() - this.period;
                return this._tracked = this._tracked.filter((function(t) {
                    return t > e
                })), !(this._tracked.length < this.rate && (this._tracked.push(Date.now()), 1))
            }
        }]) && k(t.prototype, n), r && k(t, r), e
    }();

    function P(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function L(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? P(Object(n), !0).forEach((function(t) {
                B(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : P(Object(n)).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function I(e) {
        return (I = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function R(e, t, n, r, o, i, a) {
        try {
            var c = e[i](a),
                s = c.value
        } catch (e) {
            return void n(e)
        }
        c.done ? t(s) : Promise.resolve(s).then(r, o)
    }

    function M(e) {
        return function() {
            var t = this,
                n = arguments;
            return new Promise((function(r, o) {
                var i = e.apply(t, n);

                function a(e) {
                    R(i, r, o, a, c, "next", e)
                }

                function c(e) {
                    R(i, r, o, a, c, "throw", e)
                }
                a(void 0)
            }))
        }
    }

    function D(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function N(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function F(e, t) {
        return (F = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function U(e, t, n) {
        return (U = "undefined" != typeof Reflect && Reflect.get ? Reflect.get : function(e, t, n) {
            var r = function(e, t) {
                for (; !Object.prototype.hasOwnProperty.call(e, t) && null !== (e = z(e)););
                return e
            }(e, t);
            if (r) {
                var o = Object.getOwnPropertyDescriptor(r, t);
                return o.get ? o.get.call(n) : o.value
            }
        })(e, t, n || e)
    }

    function z(e) {
        return (z = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function B(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var V = n(37)("cbio.Device"),
        W = "_cobrowse_device_registration",
        K = function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), e = function(e, t) {
                    return !t || "object" !== I(t) && "function" != typeof t ? N(e) : t
                }(this, z(t).call(this)), B(N(e), "url", (function() {
                    return "".concat(t.api, "/api/1/devices/").concat(t.deviceId())
                })), B(N(e), "reset", (function() {
                    var t = arguments.length > 0 && void 0 !== arguments[0] && arguments[0];
                    V("resetting device"), clearTimeout(e._registrationTimeout), e._registrationTimeout = null, t || (g.a.remove(W), delete e._updatedCustomData), e._socket && (e._socket.close(), e._socket = null)
                })), B(N(e), "destroy", M(regeneratorRuntime.mark((function n() {
                    return regeneratorRuntime.wrap((function(n) {
                        for (;;) switch (n.prev = n.next) {
                            case 0:
                                return V("destroying device"), e.reset(), n.abrupt("return", U(z(t.prototype), "destroy", N(e)).call(N(e)));
                            case 3:
                            case "end":
                                return n.stop()
                        }
                    }), n)
                })))), B(N(e), "hasPendingUpdates", (function() {
                    return !!e._updatedCustomData
                })), B(N(e), "_saveRegistration", (function() {
                    var t = e.field("next_registration") || 3600,
                        n = (new Date).getTime();
                    "number" != typeof Date.now() && console.error("Unexpected type returned for Date.now(), expecting number got ".concat(I(Date.now()))), "number" != typeof n && console.error("Unexpected type for getTime() of Date, expecting number got ".concat(I(n)));
                    var r = {
                        notification_url: e.field("notification_url"),
                        notification_token: e.field("notification_token"),
                        custom_data: e.field("custom_data"),
                        next_registration_time: n + 1e3 * t
                    };
                    return e.cache({
                        next_registration_time: r.next_registration_time
                    }), g.a.set(W, r), N(e)
                })), B(N(e), "_loadRegistration", (function() {
                    var t = g.a.get(W);
                    t && (V("restoring registration", t), e.cache(t))
                })), B(N(e), "updateRegistration", function() {
                    var n = M(regeneratorRuntime.mark((function n(r) {
                        return regeneratorRuntime.wrap((function(n) {
                            for (;;) switch (n.prev = n.next) {
                                case 0:
                                    if (!e._rateLimiter.limit()) {
                                        n.next = 2;
                                        break
                                    }
                                    throw new Error("Cobrowse update rate limit exceeded. Maybe you're updating custom data too often?");
                                case 2:
                                    return V("updating registration"), n.next = 5, e.update({
                                        device: t.info,
                                        custom_data: L({}, e.customData, {}, e._updatedCustomData)
                                    }, r);
                                case 5:
                                    return delete e._updatedCustomData, e._updateNotificationSocket(), e._saveRegistration(), n.abrupt("return", N(e));
                                case 9:
                                case "end":
                                    return n.stop()
                            }
                        }), n)
                    })));
                    return function(e) {
                        return n.apply(this, arguments)
                    }
                }()), B(N(e), "runRegistrationLoop", M(regeneratorRuntime.mark((function t() {
                    return regeneratorRuntime.wrap((function(t) {
                        for (;;) switch (t.prev = t.next) {
                            case 0:
                                if (V("starting registrations"), e.reset(!0), !(e.hasPendingUpdates() || e.nextRegistration() <= 0)) {
                                    t.next = 5;
                                    break
                                }
                                return t.next = 5, e.updateRegistration().catch((function() {}));
                            case 5:
                                e._updateNotificationSocket(), e._loop();
                            case 7:
                            case "end":
                                return t.stop()
                        }
                    }), t)
                })))), B(N(e), "pauseRegistrationLoop", M(regeneratorRuntime.mark((function t() {
                    return regeneratorRuntime.wrap((function(t) {
                        for (;;) switch (t.prev = t.next) {
                            case 0:
                                V("pausing registrations"), e.reset(!0);
                            case 2:
                            case "end":
                                return t.stop()
                        }
                    }), t)
                })))), B(N(e), "nextRegistration", (function() {
                    return (e.field("next_registration_time") || 0) - (new Date).getTime()
                })), B(N(e), "_nextUpdate", (function() {
                    var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                    return Math.max(Math.min(6e4 * t, 36e5), e.nextRegistration())
                })), B(N(e), "_loop", (function() {
                    var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                    if (e._registrationTimeout) console.error("CobrowseIO: tried to schedule registration while already scheduled");
                    else {
                        var n = e._nextUpdate(t);
                        e._registrationTimeout = setTimeout(M(regeneratorRuntime.mark((function n() {
                            return regeneratorRuntime.wrap((function(n) {
                                for (;;) switch (n.prev = n.next) {
                                    case 0:
                                        if (e._registrationTimeout) {
                                            n.next = 2;
                                            break
                                        }
                                        return n.abrupt("return", console.error("Registration loop cancelled"));
                                    case 2:
                                        return n.prev = 2, n.next = 5, e.updateRegistration();
                                    case 5:
                                        return e._registrationTimeout = !1, n.abrupt("return", e._loop());
                                    case 9:
                                        return n.prev = 9, n.t0 = n.catch(2), console.warn("CobrowseIO: registration failed", n.t0), e._registrationTimeout = !1, n.abrupt("return", e._loop(t + 1));
                                    case 14:
                                    case "end":
                                        return n.stop()
                                }
                            }), n, null, [
                                [2, 9]
                            ])
                        }))), Math.max(1e3, n))
                    }
                })), B(N(e), "_socketUrl", (function() {
                    var t = e.field("notification_url"),
                        n = e.field("notification_token");
                    return m(t, n)
                })), B(N(e), "_updateNotificationSocket", (function() {
                    var t = e.field("notification_url"),
                        n = e.field("notification_token");
                    t && n && !e._socket ? (e._socket = new A(e._socketUrl), e._socket.setMaxReconnectDelay(6e4), e._socket.on("error", (function(e) {
                        e && console.warn("CobrowseIO notification socket error:", e)
                    })), e._socket.on("notification", (function(t) {
                        e.emit("notification", t)
                    })), e._socket.on("probe", (function(t) {
                        e._socket.send("alive", t)
                    })), e._socket.on("open", (function() {
                        e._socket.send("filter", {
                            events: ["probe", "notification"]
                        })
                    }))) : t && n || (e._socket && (console.log("CobrowseIO: Closing socket"), e._socket.close()), e._socket = null)
                })), V("constructed"), e._loadRegistration(), e._rateLimiter = new T(5, 12e4), e
            }
            var r, o, i;
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && F(e, t)
            }(t, e), r = t, i = [{
                key: "deviceId",
                value: function() {
                    var e = g.a.get("_cobrowse_device_id") || Object(y.a)();
                    return g.a.set("_cobrowse_device_id", e), e
                }
            }, {
                key: "info",
                get: function() {
                    var e = n(45).default;
                    return {
                        platform: "web",
                        device: window.navigator.userAgent,
                        device_locale: window.navigator.language,
                        device_timezone: window.Intl.DateTimeFormat().resolvedOptions().timeZone,
                        os_version: window.navigator.platform,
                        app_id: window.location.origin,
                        app_name: window.document.title,
                        app_installation_id: t.deviceId(),
                        app_build_configuration: "release",
                        sdk_version: e.version,
                        push_enabled: !1
                    }
                }
            }], (o = [{
                key: "customData",
                set: function(e) {
                    V("updating custom data", e);
                    var t = this.customData;
                    Object.keys(e).find((function(n) {
                        return t[n] !== e[n]
                    })) && (this._updatedCustomData = L({}, this._updatedCustomData, {}, e))
                },
                get: function() {
                    return this.field("custom_data") || {}
                }
            }]) && D(r.prototype, o), i && D(r, i), t
        }(v);

    function q(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function Y(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var X = function() {
            function e(t) {
                var n = this;
                ! function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, e), Y(this, "open", (function() {
                    n._session.isEnded() || (n._openControl(), n._openStream())
                })), Y(this, "close", (function() {
                    window.removeEventListener("visibilitychange", n.onVisibilityChange), n._session && (n._session.removeListener("updated", n.open), n._session = null), n._stream && (n._stream.close(), n._stream = null), n._control && (n._control.close(), n._control = null)
                })), Y(this, "_socket", (function(e, t) {
                    var r = new A((function() {
                        return m(e, n._session.field(t))
                    }));
                    return r.on("error", (function(e) {
                        console.warn("CobrowseIO session socket error:", e)
                    })), r
                })), Y(this, "_openControl", (function() {
                    var e = n._session.field("control_url"),
                        t = n._session.field("control_token");
                    e && t && !n._control && (n._control = n._socket(e, "control_token"), n._control.on("open", (function() {
                        n._control.send("filter", {
                            events: ["session"]
                        }), n._session.fetch().catch((function(e) {
                            console.warn("CobrowseIO failed fetching session state", e)
                        }))
                    })), n._control.on("session", (function(e) {
                        n._session.updateResource(e)
                    })))
                })), Y(this, "_openStream", (function() {
                    var e = n._session.field("stream_url"),
                        t = n._session.field("stream_token");
                    if (e && t && !n._stream) {
                        n._stream = n._socket(e, "stream_token");
                        var r = ["drawing", "laser", "url"];
                        r.forEach((function(e) {
                            n._stream.on(e, (function(t) {
                                n._session.emit(e, t)
                            }))
                        }));
                        var o = ["touch", "mouse", "keypress", "scroll", "focus", "input", "change"];
                        o.forEach((function(e) {
                            n._stream.on(e, (function(t) {
                                n._session.emit("control", e, t)
                            }))
                        })), n._stream.on("sync", (function(e) {
                            n._session.emit("sync", e)
                        })), n._stream.on("probe", (function(e) {
                            n._stream.send("alive", e)
                        })), n._stream.on("open", (function() {
                            n._stream.send("filter", {
                                events: [].concat(o, r, ["sync", "probe"])
                            })
                        }))
                    }
                })), this._session = t, t.on("updated", this.open), this.open()
            }
            var t, n, r;
            return t = e, (n = [{
                key: "stream",
                get: function() {
                    return this._stream
                }
            }]) && q(t.prototype, n), r && q(t, r), e
        }(),
        H = n(19),
        $ = n(88),
        G = n(33);

    function J(e) {
        return (J = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function Z(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function Q(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function ee(e, t) {
        return (ee = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function te(e, t, n) {
        return (te = "undefined" != typeof Reflect && Reflect.get ? Reflect.get : function(e, t, n) {
            var r = function(e, t) {
                for (; !Object.prototype.hasOwnProperty.call(e, t) && null !== (e = ne(e)););
                return e
            }(e, t);
            if (r) {
                var o = Object.getOwnPropertyDescriptor(r, t);
                return o.get ? o.get.call(n) : o.value
            }
        })(e, t, n || e)
    }

    function ne(e) {
        return (ne = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function re(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var oe = n(37)("cbio.Session"),
        ie = function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), e = function(e, t) {
                    return !t || "object" !== J(t) && "function" != typeof t ? Q(e) : t
                }(this, ne(t).call(this)), re(Q(e), "activate", (function() {
                    oe("activate session");
                    var t = n(45).default;
                    return e.update({
                        state: "active",
                        device: K.info,
                        custom_data: t._device.customData
                    })
                })), re(Q(e), "end", (function() {
                    return oe("end session"), e.update({
                        state: "ended"
                    })
                })), re(Q(e), "code", (function() {
                    return e.field("code")
                })), re(Q(e), "state", (function() {
                    return e.field("state")
                })), re(Q(e), "isActive", (function() {
                    return "active" === e.state()
                })), re(Q(e), "isAuthorizing", (function() {
                    return "authorizing" === e.state()
                })), re(Q(e), "isPending", (function() {
                    return "pending" === e.state()
                })), re(Q(e), "isEnded", (function() {
                    return "ended" === e.state()
                })), re(Q(e), "selectors", (function() {
                    return e.field("redaction_selectors") || []
                })), re(Q(e), "agent", (function() {
                    return e.field("agent") || !1
                })), re(Q(e), "requireConsent", (function() {
                    return e.field("require_consent")
                })), re(Q(e), "updateResource", (function(n) {
                    var r = e.isEnded();
                    return te(ne(t.prototype), "updateResource", Q(e)).call(Q(e), n), e.isActive() && e._startFrames(), e.isEnded() && !r && (e.emit("ended", Q(e)), e.cleanup()), Q(e)
                })), re(Q(e), "_handleSync", (function(t) {
                    var n = t.id;
                    n && e._lastSyncId === n || (e._lastSyncId = n, e._frameLoop.invalidateFrame())
                })), re(Q(e), "_sendEvent", (function(t, n) {
                    return !!e.isActive() && (!!H.a.isActive() && (!!e._sockets && (!!e._sockets.stream && (!!e._sockets.stream.connected && (!(!e._sockets.stream.bufferedAmount > 100) && (e._sockets.stream.send(t, n), !0))))))
                })), re(Q(e), "_sendScreenEvent", (function() {
                    return e._sendEvent("screen", {
                        type: "Screen",
                        timestamp: new Date,
                        url: window.location.href,
                        title: document.title
                    })
                })), re(Q(e), "_sendFrame", (function(t) {
                    return e._frameId = (e._frameId || 0) + 1, e._sendEvent("frame", {
                        type: "Frame",
                        id: e._frameId,
                        dom: t,
                        timestamp: new Date,
                        mime_type: "application/x-cbio-vdom-patch"
                    })
                })), re(Q(e), "_sendMouseEvent", (function(t) {
                    var n = "mouseout";
                    return t.in && (n = t.down ? "mousedown" : "mouseup"), e._sendEvent("mouse", {
                        type: "Mouse",
                        x: t.x,
                        y: t.y,
                        state: n,
                        timestamp: new Date
                    })
                })), re(Q(e), "_startFrames", (function() {
                    return oe("start frames"), e.isActive() ? (e._frameLoop.isRunning() || (e._frameLoop.start(), G.a.onChange = e._sendMouseEvent), Q(e)) : Q(e)
                })), oe("session constructed"), e._sockets = new X(Q(e)), e._frameLoop = new $.a(e._sendFrame), window.addEventListener("hashchange", e._sendScreenEvent, {
                    capture: !0,
                    passive: !0
                }), e.on("sync", e._handleSync), e
            }
            var r, o, i;
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && ee(e, t)
            }(t, e), r = t, (o = [{
                key: "url",
                value: function() {
                    var e = this.id() || this.code();
                    return "".concat(t.api, "/api/1/sessions").concat(e ? "/".concat(e) : "")
                }
            }, {
                key: "cleanup",
                value: function() {
                    oe("cleanup session"), this._sockets && this._sockets.close(), this._sockets = null, this._frameLoop && this._frameLoop.destroy(), this._frameLoop = null, G.a.onChange === this._sendMouseEvent && (G.a.onChange = null), window.removeEventListener("hashchange", this._sendScreenEvent, {
                        capture: !0,
                        passive: !0
                    }), this.removeAllListeners()
                }
            }]) && Z(r.prototype, o), i && Z(r, i), t
        }(v);

    function ae(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var ce = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), ae(this, "el", (function() {
            return t._el || (t._el = document.createElement("div"), t._el.className = "__cbio_ignored", t._el.style.border = "border: 1px solid white", t._el.style.borderRadius = "15px", t._el.style.background = "red", t._el.style.position = "fixed", t._el.style.zIndex = "2147483647", t._el.style.height = "15px", t._el.style.width = "15px", t._el.style.marginLeft = "-7.5px", t._el.style.marginTop = "-7.5px"), t._el
        })), ae(this, "update", (function(e) {
            H.a.isActive() && (document.body.appendChild(t.el()), "end" === e.phase ? t.hide() : (t.el().style.left = "".concat(Math.round(e.x * window.innerWidth), "px"), t.el().style.top = "".concat(Math.round(e.y * window.innerHeight), "px")))
        })), ae(this, "hide", (function() {
            t.el().parentNode && t.el().parentNode.removeChild(t.el())
        }))
    };

    function se(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var ue = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), se(this, "el", (function() {
            return t._el || (t._el = document.createElement("div"), t._el.className = "__cbio_ignored cbio_session_controls", t._el.textContent = "End Cobrowse Session", t._el.style.fontFamily = "sans-serif", t._el.style.padding = "10px 13px", t._el.style.fontSize = "13px", t._el.style.whiteSpace = "nowrap", t._el.style.color = "white", t._el.style.boxShadow = "0px 2px 5px #33333344", t._el.style.cursor = "pointer", t._el.style.borderRadius = "30px", t._el.style.background = "rgb(234, 91, 80)", t._el.style.position = "fixed", t._el.style.zIndex = "2147483647", t._el.style.bottom = "20px", t._el.style.left = "50%", t._el.style.transform = "translateX(-50%)", t._el.addEventListener("click", t._onEnd)), t._el
        })), se(this, "_onEnd", (function() {
            var e = n(45).default.currentSession;
            e && e.isActive() && e.end()
        })), se(this, "show", (function() {
            document.body.appendChild(t.el())
        })), se(this, "hide", (function() {
            t.el().parentNode && t.el().parentNode.removeChild(t.el())
        }))
    };

    function fe(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var le = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), fe(this, "content", '\n        <div class="cobrowse-dialog" style="color: #333; font-family:sans-serif; line-height:140%; position:fixed; padding:25px; background:white; border-radius:15px; z-index:2147483647; top:50px; left:50%; width:75%; max-width:350px; transform:translateX(-50%); box-shadow:0px 0px 15px #33333322;">\n            <div style="text-align:center; margin-top:10px; margin-bottom:20px"}><b>Support Request</b></div>\n            <div>A support agent would like to temporarily use this web page with you. Do you want to allow this?</div>\n            <div style=\'float:right; margin-top:40px; color:rgb(0, 122, 255);\'>\n                <a class="cobrowse-button cobrowse-deny" style="cursor:pointer; padding:10px;">Deny</a>\n                <a class="cobrowse-button cobrowse-allow" style="cursor:pointer; padding:10px; font-weight: bold;">Allow</a>\n            </div>\n        </div>\n    '), fe(this, "container", (function() {
            return t._container || (t._container = document.createElement("div"), t._container.style.background = "rgba(50, 50, 50, 0.4)", t._container.style.position = "fixed", t._container.style.zIndex = "2147483647", t._container.style.bottom = "0", t._container.style.top = "0", t._container.style.left = "0", t._container.style.right = "0"), t._container
        })), fe(this, "show", (function(e, n) {
            var r = t.container();
            t._container.innerHTML = t.content, r.querySelector(".cobrowse-allow").addEventListener("click", (function() {
                e(), t.hide()
            })), r.querySelector(".cobrowse-deny").addEventListener("click", (function() {
                n(), t.hide()
            })), document.body.appendChild(r)
        })), fe(this, "hide", (function() {
            t.container().parentNode && t.container().parentNode.removeChild(t.container())
        }))
    };
    n(204), n(206), n(211), n(213), n(214), n(215), n(216), n(217), n(218), n(219), n(220), n(221), n(222), n(223), n(225), n(226), n(227), n(228), n(229), n(230), n(231), n(232), n(233), n(234), n(235);

    function de(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var pe = new function e() {
            var t = this;
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), de(this, "el", (function() {
                return t._el || (t._el = document.createElement("img"), t._el.className = "__cbio_ignored", t._el.style.pointerEvents = "none", t._el.style.width = "100%", t._el.style.height = "100%", t._el.style.position = "fixed", t._el.style.zIndex = "2147483647", t._el.style.top = "0", t._el.style.left = "0"), t._el
            })), de(this, "_arrayBufferToBase64", (function(e) {
                for (var t = "", n = new Uint8Array(e), r = n.byteLength, o = 0; o < r; o += 1) t += String.fromCharCode(n[o]);
                return window.btoa(t)
            })), de(this, "update", (function(e) {
                if (H.a.isActive())
                    if (e.image) {
                        t.el().style.left = "".concat(100 * e.x, "%"), t.el().style.top = "".concat(100 * e.y, "%"), t.el().style.height = "".concat(100 * e.height, "%"), t.el().style.width = "".concat(100 * e.width, "%");
                        var n = "data:".concat(e.mime_type, ";base64,").concat(t._arrayBufferToBase64(e.image));
                        t.el().src = n, document.body.appendChild(t.el())
                    } else t.hide()
            })), de(this, "hide", (function() {
                t.el().parentNode && t.el().parentNode.removeChild(t.el())
            }))
        },
        he = n(15);

    function ve(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var ye = new function e() {
            var t = this;
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), ve(this, "el", (function() {
                return t._el || (t._el = document.createElementNS("http://www.w3.org/2000/svg", "svg"), t._el.setAttribute("class", "__cbio_ignored"), t._el.setAttribute("viewBox", "0 0 320 512"), t._el.style.position = "fixed", t._el.style.zIndex = "2147483647", t._el.style.height = "25px", t._el.style.width = "25px", t._el.style.marginLeft = "-7px", t._el.style.marginTop = "-1px", t._el.style.opacity = .7, t._path = document.createElementNS("http://www.w3.org/2000/svg", "path"), t._path.setAttribute("d", "M302.189 329.126H196.105l55.831 135.993c3.889 9.428-.555 19.999-9.444 23.999l-49.165 21.427c-9.165 4-19.443-.571-23.332-9.714l-53.053-129.136-86.664 89.138C18.729 472.71 0 463.554 0 447.977V18.299C0 1.899 19.921-6.096 30.277 5.443l284.412 292.542c11.472 11.179 3.007 31.141-12.5 31.141z"), t._path.setAttribute("stroke", "white"), t._path.setAttribute("stroke-width", "40"), t._path.setAttribute("opacity", 1), t._path.setAttribute("fill", "blue"), t._el.appendChild(t._path)), t._el
            })), ve(this, "_updatePosition", (function(e) {
                var n = e.x,
                    r = e.y,
                    o = he.a.getById(e.target.id);
                if (o) {
                    var i = o.ownerDocument,
                        a = i && i.defaultView && i.defaultView.frameElement;
                    if (a) {
                        var c = a.getBoundingClientRect();
                        n += Math.round(c.left), r += Math.round(c.top)
                    }
                    t.el().style.top = "".concat(r, "px"), t.el().style.left = "".concat(n, "px")
                }
            })), ve(this, "update", (function(e, n) {
                if ("mouse" === e && H.a.isActive()) switch (document.body.appendChild(t.el()), n.state) {
                    case "mousedown":
                        t.el().style.opacity = 1;
                        break;
                    case "mouseup":
                        t.el().style.opacity = .7;
                        break;
                    case "mousemove":
                        t._updatePosition(n)
                }
            })), ve(this, "hide", (function() {
                t.el().parentNode && t.el().parentNode.removeChild(t.el())
            }))
        },
        ge = n(81);

    function me(e) {
        return (me = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function be(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    var we = n(37)("cbio.Integrations"),
        _e = function() {
            function e() {
                ! function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, e)
            }
            var t, r, o;
            return t = e, o = [{
                key: "watchProperty",
                value: function(e, t, n) {
                    e[t] ? n() : Object.defineProperty(e, t, {
                        configurable: !0,
                        set: function(r) {
                            we("CobrowseIO detected", t), Object.defineProperty(e, t, {
                                configurable: !0,
                                writable: !0,
                                value: r
                            }), setTimeout(n, 0)
                        }
                    })
                }
            }, {
                key: "initialize",
                value: function() {
                    var e = this;
                    this.watchProperty(window, "Intercom", (function() {
                        return e.intercom()
                    })), this.watchProperty(window, "$zopim", (function() {
                        return e.zendesk()
                    })), this.watchProperty(window, "embedded_svc", (function() {
                        return e.watchProperty(window.embedded_svc, "liveAgentAPI", (function() {
                            return e.salesforce()
                        }))
                    }))
                }
            }, {
                key: "intercom",
                value: function() {
                    try {
                        if (!window.Intercom || "function" != typeof window.Intercom) throw new Error("expected window.Intercom to be a function");
                        window.Intercom("boot", {
                            CobrowseID: K.deviceId()
                        })
                    } catch (e) {
                        console.error("Error syncing Cobrowse.io with Intercom, please contact hello@cobrowse.io for help!"), console.warn(e)
                    }
                }
            }, {
                key: "zendesk",
                value: function() {
                    var e = n(45).default;
                    try {
                        if ("function" != typeof window.$zopim) throw new Error("expected window.$zopim to a be a function");
                        window.$zopim((function() {
                            window.$zopim.livechat.addTags("cobrowseio_deviceid_".concat(K.deviceId())), e.customData = {
                                cobrowseio_deviceid: K.deviceId()
                            }
                        }))
                    } catch (e) {
                        console.error("Error syncing Cobrowse.io with Zendesk Chat, please contact hello@cobrowse.io for help!"), console.warn(e)
                    }
                }
            }, {
                key: "salesforce",
                value: function() {
                    var e = n(45).default;
                    try {
                        if ("object" !== me(window.embedded_svc)) throw new Error("expected window.embedded_svc to a be an object");
                        if (window.embedded_svc && !window.embedded_svc.liveAgentAPI) throw new Error("expected liveAgentAPI on embedded_svc");
                        window.embedded_svc.addEventHandler("onChatRequestSuccess", (function(t) {
                            t && t.liveAgentSessionKey && (e.customData = {
                                salesforce_liveAgentSessionKey: t.liveAgentSessionKey
                            })
                        })), window.embedded_svc.addEventHandler("onChatEndedByAgent", (function() {
                            e.currentSession && e.currentSession.end()
                        })), window.embedded_svc.addEventHandler("onChatEndedByChasitor", (function() {
                            e.currentSession && e.currentSession.end()
                        }))
                    } catch (e) {
                        console.error("Error syncing Cobrowse.io with Salesforce Chat, please contact hello@cobrowse.io for help!"), console.warn(e)
                    }
                }
            }], (r = null) && be(t.prototype, r), o && be(t, o), e
        }(),
        Oe = n(26),
        Ee = (n(236), n(237), document.currentScript && document.currentScript.src),
        Se = Ee ? new URL(Ee) : null,
        xe = n(38);

    function Ce(e, t, n, r, o, i, a) {
        try {
            var c = e[i](a),
                s = c.value
        } catch (e) {
            return void n(e)
        }
        c.done ? t(s) : Promise.resolve(s).then(r, o)
    }

    function je(e) {
        return function() {
            var t = this,
                n = arguments;
            return new Promise((function(r, o) {
                var i = e.apply(t, n);

                function a(e) {
                    Ce(i, r, o, a, c, "next", e)
                }

                function c(e) {
                    Ce(i, r, o, a, c, "throw", e)
                }
                a(void 0)
            }))
        }
    }
    var Ae = function(e) {
        return ke.apply(this, arguments)
    };

    function ke() {
        return (ke = je(regeneratorRuntime.mark((function e(t) {
            var n, r, o;
            return regeneratorRuntime.wrap((function(e) {
                for (;;) switch (e.prev = e.next) {
                    case 0:
                        return Se && Se.origin.endsWith(".cobrowse.io") || console.warn("CobrowseIO: SDK was not loaded from a cobrowse.io domain. Please see https://cobrowse.io/docs"), xe.a.isLocalResource(window.location.href) && console.warn("CobrowseIO: This page was loaded from a local address, Cobrowse may not work as expected due to inaccessible resources. We recommend using ngrok for testing development versions."), n = Se && Se.origin && "".concat(Se.origin, "/version"), e.next = 5, fetch(n || "https://js.cobrowse.io/version");
                    case 5:
                        return r = e.sent, e.next = 8, r.text();
                    case 8:
                        return (o = e.sent) !== t && console.warn("CobrowseIO: A newer SDK is available: ".concat(o)), e.abrupt("return", o);
                    case 11:
                    case "end":
                        return e.stop()
                }
            }), e)
        })))).apply(this, arguments)
    }

    function Te(e) {
        return (Te = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function Pe(e) {
        return (Pe = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function Le(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function Ie(e, t) {
        return (Ie = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function Re(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var Me = n(37)("cbio.ActivityDetection"),
        De = new(function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), e = function(e, t) {
                    return !t || "object" !== Te(t) && "function" != typeof t ? Le(e) : t
                }(this, Pe(t).call(this)), Re(Le(e), "start", (function() {
                    e._started || (e._started = !0, Me("started"), document.body.addEventListener("mouseenter", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.addEventListener("mouseleave", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.addEventListener("mousemove", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.addEventListener("touchstart", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.addEventListener("touchend", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.addEventListener("touchmove", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.addEventListener("visibilitychange", e._onActivity, {
                        capture: !0
                    }), e._onActivity())
                })), Re(Le(e), "stop", (function() {
                    e._active = !1, e._started = !1, Me("stopped"), e.removeAllListeners(), document.body.removeEventListener("mouseenter", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.removeEventListener("mouseleave", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.removeEventListener("mousemove", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.removeEventListener("touchstart", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.removeEventListener("touchend", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.body.removeEventListener("touchmove", e._onActivity, {
                        capture: !0,
                        passive: !0
                    }), document.removeEventListener("visibilitychange", e._onActivity, {
                        capture: !0
                    })
                })), Re(Le(e), "isActive", (function() {
                    return e._active
                })), Re(Le(e), "_onActivity", (function() {
                    e._makeActive(), clearTimeout(e._activeTimeout), e._activeTimeout = setTimeout(e._makeInactive, e._inactiveTime)
                })), Re(Le(e), "_makeActive", (function() {
                    e._active || (document.hidden ? Me("window is hidden, not becoming active") : (e._active = !0, Me("became active"), e.emit("active")))
                })), Re(Le(e), "_makeInactive", (function() {
                    e._active && (e._active = !1, Me("became inactive"), e.emit("inactive"))
                })), e._started = !1, e._active = !1, e._activeTimeout = null, e._inactiveTime = 9e5, e
            }
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && Ie(e, t)
            }(t, e), t
        }(a.EventEmitter)),
        Ne = n(53);
    n(147), n(148);

    function Fe(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    var Ue = function() {
        function e() {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e)
        }
        var t, n, r;
        return t = e, r = [{
            key: "isHeadless",
            value: function() {
                return navigator.userAgent.toLowerCase().includes("headless")
            }
        }], (n = null) && Fe(t.prototype, n), r && Fe(t, r), e
    }();

    function ze(e, t, n, r, o, i, a) {
        try {
            var c = e[i](a),
                s = c.value
        } catch (e) {
            return void n(e)
        }
        c.done ? t(s) : Promise.resolve(s).then(r, o)
    }

    function Be(e) {
        return function() {
            var t = this,
                n = arguments;
            return new Promise((function(r, o) {
                var i = e.apply(t, n);

                function a(e) {
                    ze(i, r, o, a, c, "next", e)
                }

                function c(e) {
                    ze(i, r, o, a, c, "throw", e)
                }
                a(void 0)
            }))
        }
    }

    function Ve(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }

    function We(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var Ke = n(37)("cbio.CobrowseIO"),
        qe = "_cobrowse_active_session",
        Ye = function() {
            function e() {
                var t = this;
                ! function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, e), We(this, "start", (function() {
                    var n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
                    if (Ke("start()", n, "in", document), !Ue.isHeadless() || n.allowHeadless) {
                        if (Ne.default.enableReceive(), window.self !== window.top) {
                            if (!n.allowIFrameStart) return Object(i.isxdoc)(window.parent) ? (Ke("Starting Cobrowse in Iframe bridge mode with trusted origins:", Ne.default.trustedOrigins, "for", document), void Ne.default.enableBroadcast()) : void Ke("Not starting Cobrowse for same-origin iframe", document);
                            H.a.setTracksIframes(!0)
                        }
                        t.license ? t._started || (t._started = !0, H.a.start(), Ae(e.version).catch((function() {})), console.log("CobrowseIO started", e.version), t._device.on("notification", (function(e) {
                            var n = e["cobrowseio-code"];
                            n && t.joinSession(n).catch((function(e) {
                                console.warn(e)
                            }))
                        })), "loading" !== document.readyState ? t._restoreCurrentSession() : document.addEventListener("DOMContentLoaded", t._restoreCurrentSession), De.on("active", (function() {
                            return t._device.runRegistrationLoop()
                        })), De.on("inactive", (function() {
                            return t._device.pauseRegistrationLoop()
                        })), De.start(), _e.initialize()) : console.error("CobrowseIO.license must be set before calling start")
                    } else console.warn("Not starting Cobrowse in a headless browser")
                })), We(this, "stop", Be(regeneratorRuntime.mark((function e() {
                    return regeneratorRuntime.wrap((function(e) {
                        for (;;) switch (e.prev = e.next) {
                            case 0:
                                if (!t.currentSession || t.currentSession.isEnded()) {
                                    e.next = 4;
                                    break
                                }
                                return e.next = 3, t.currentSession.end().catch((function(e) {
                                    console.warn("Failed to end session on stop", e.getMessage())
                                }));
                            case 3:
                                t.currentSession.cleanup();
                            case 4:
                                return t._currentSession = null, g.a.remove(qe), H.a.stop(), De.stop(), e.next = 10, t._device.destroy();
                            case 10:
                                return t._deviceInstance = null, t._started = !1, e.abrupt("return", t);
                            case 13:
                            case "end":
                                return e.stop()
                        }
                    }), e)
                })))), We(this, "_updateSessionControls", (function() {
                    var e = t.currentSession;
                    e && e.isActive() ? t.showSessionControls(e) : t.hideSessionControls(e)
                })), We(this, "_updateRedaction", (function() {
                    var e = t.currentSession;
                    e && Oe.a.setSelectors(e.selectors())
                })), We(this, "_setCurrentSession", (function(e) {
                    t._currentSession && t._currentSession.id() === e.id() || (t._currentSession && t._currentSession.cleanup(), t._currentSession = e, t._runConfirmSession(e), e.on("updated", t._runConfirmSession), t._serializeSession(), e.on("updated", t._serializeSession), t._updateSessionControls(), e.on("updated", t._updateSessionControls), t._updateRedaction(), e.on("updated", t._updateRedaction), e.on("laser", ce.update), e.once("ended", ce.hide), e.on("drawing", pe.update), e.once("ended", pe.hide), e.on("control", ye.update), e.once("ended", ye.hide), e.on("control", ge.a.applyEvent), e.on("control", (function(e, t) {
                        return Ne.default.broadcastToChildren("control", [e, t])
                    })))
                })), We(this, "_serializeSession", (function() {
                    var e = t.currentSession;
                    if (e)
                        if (e.isActive()) g.a.set(qe, e.resource);
                        else {
                            var n = g.a.get(qe);
                            n && n.id === e.id() && g.a.remove(qe)
                        }
                    else g.a.remove(qe)
                })), We(this, "_restoreCurrentSession", (function() {
                    if (!t._currentSessionRestored) {
                        t._currentSessionRestored = !0;
                        var e = g.a.get(qe);
                        if (e) {
                            var n = (new ie).updateResource(e);
                            n.fetch().catch((function(e) {
                                console.warn(e)
                            })), t._setCurrentSession(n)
                        }
                    }
                })), We(this, "hideSessionControls", (function() {
                    ue.hide()
                })), We(this, "_updateCustomData", o()((function() {
                    t._started && t._device.updateRegistration().catch((function(e) {
                        console.warn("CobrowseIO: updating customData failed", e)
                    }))
                }), 15e3, {
                    leading: !0,
                    trailing: !0
                })), We(this, "_runConfirmSession", function() {
                    var e = Be(regeneratorRuntime.mark((function e(n) {
                        return regeneratorRuntime.wrap((function(e) {
                            for (;;) switch (e.prev = e.next) {
                                case 0:
                                    if (n) {
                                        e.next = 2;
                                        break
                                    }
                                    return e.abrupt("return");
                                case 2:
                                    if (n === t.currentSession) {
                                        e.next = 4;
                                        break
                                    }
                                    return e.abrupt("return");
                                case 4:
                                    if (n.agent()) {
                                        e.next = 6;
                                        break
                                    }
                                    return e.abrupt("return");
                                case 6:
                                    if (!n.isPending() && !n.isAuthorizing()) {
                                        e.next = 34;
                                        break
                                    }
                                    if (n.requireConsent()) {
                                        e.next = 11;
                                        break
                                    }
                                    return e.next = 10, n.activate();
                                case 10:
                                    return e.abrupt("return");
                                case 11:
                                    if (!t._confirming) {
                                        e.next = 13;
                                        break
                                    }
                                    return e.abrupt("return");
                                case 13:
                                    return t._confirming = !0, e.prev = 14, e.next = 17, n.update({
                                        state: "authorizing"
                                    });
                                case 17:
                                    return e.next = 19, t.confirmSession(n);
                                case 19:
                                    if (n.isEnded()) {
                                        e.next = 22;
                                        break
                                    }
                                    return e.next = 22, n.activate();
                                case 22:
                                    e.next = 29;
                                    break;
                                case 24:
                                    return e.prev = 24, e.t0 = e.catch(14), e.t0 && console.error("CobrowseIO: error caught during confirmation", e.t0), e.next = 29, n.end();
                                case 29:
                                    return e.prev = 29, t._confirming = !1, e.finish(29);
                                case 32:
                                    e.next = 36;
                                    break;
                                case 34:
                                    t._confirming = !1, le.hide();
                                case 36:
                                case "end":
                                    return e.stop()
                            }
                        }), e, null, [
                            [14, 24, 29, 32]
                        ])
                    })));
                    return function(t) {
                        return e.apply(this, arguments)
                    }
                }()), We(this, "confirmSession", Be(regeneratorRuntime.mark((function e() {
                    return regeneratorRuntime.wrap((function(e) {
                        for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.abrupt("return", new Promise((function(e, t) {
                                    le.show(e, t)
                                })));
                            case 1:
                            case "end":
                                return e.stop()
                        }
                    }), e)
                })))), We(this, "joinSession", function() {
                    var e = Be(regeneratorRuntime.mark((function e(n) {
                        return regeneratorRuntime.wrap((function(e) {
                            for (;;) switch (e.prev = e.next) {
                                case 0:
                                    if (!t.currentSession || t.currentSession.id() !== n && t.currentSession.code() !== n) {
                                        e.next = 2;
                                        break
                                    }
                                    return e.abrupt("return", t.currentSession);
                                case 2:
                                    return e.abrupt("return", t.getSession(n));
                                case 3:
                                case "end":
                                    return e.stop()
                            }
                        }), e)
                    })));
                    return function(t) {
                        return e.apply(this, arguments)
                    }
                }()), We(this, "getSession", function() {
                    var e = Be(regeneratorRuntime.mark((function e(n) {
                        var r;
                        return regeneratorRuntime.wrap((function(e) {
                            for (;;) switch (e.prev = e.next) {
                                case 0:
                                    if (t._started) {
                                        e.next = 2;
                                        break
                                    }
                                    throw new Error("CobrowseIO not started. Call CobrowseIO.start()");
                                case 2:
                                    if (!t.currentSession || t.currentSession.id() !== n && t.currentSession.code() !== n) {
                                        e.next = 4;
                                        break
                                    }
                                    return e.abrupt("return", t.currentSession.fetch());
                                case 4:
                                    if (!t.currentSession || !t.currentSession.isActive() && !t.currentSession.isAuthorizing()) {
                                        e.next = 6;
                                        break
                                    }
                                    throw new Error("Already in a session");
                                case 6:
                                    return r = (new ie).updateResource({
                                        id: n
                                    }), t._setCurrentSession(r), e.abrupt("return", r.fetch());
                                case 9:
                                case "end":
                                    return e.stop()
                            }
                        }), e)
                    })));
                    return function(t) {
                        return e.apply(this, arguments)
                    }
                }()), We(this, "createSession", Be(regeneratorRuntime.mark((function e() {
                    var n;
                    return regeneratorRuntime.wrap((function(e) {
                        for (;;) switch (e.prev = e.next) {
                            case 0:
                                if (t._started) {
                                    e.next = 2;
                                    break
                                }
                                throw new Error("CobrowseIO not started. Call CobrowseIO.start()");
                            case 2:
                                if (!t.currentSession || !t.currentSession.isActive() && !t.currentSession.isAuthorizing()) {
                                    e.next = 4;
                                    break
                                }
                                throw new Error("Already in a session");
                            case 4:
                                return e.next = 6, ie.create();
                            case 6:
                                return n = e.sent, t._setCurrentSession(n), e.abrupt("return", n);
                            case 9:
                            case "end":
                                return e.stop()
                        }
                    }), e)
                })))), We(this, "createSessionCode", Be(regeneratorRuntime.mark((function e() {
                    var n;
                    return regeneratorRuntime.wrap((function(e) {
                        for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2, t.createSession();
                            case 2:
                                return n = e.sent, e.abrupt("return", n.code());
                            case 4:
                            case "end":
                                return e.stop()
                        }
                    }), e)
                })))), We(this, "client", Be(regeneratorRuntime.mark((function e() {
                    return regeneratorRuntime.wrap((function(e) {
                        for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.abrupt("return", t);
                            case 1:
                            case "end":
                                return e.stop()
                        }
                    }), e)
                })))), We(this, "_applySettingsFrom", (function(e) {
                    e && ["license", "api", "customData", "showSessionControls", "hideSessionControls", "confirmSession", "trustedOrigins"].forEach((function(n) {
                        e[n] && (t[n] = e[n])
                    }))
                })), Ke("interface constructed in", document), this.api = "https://api.cobrowse.io", this._applySettingsFrom(window && window.CobrowseIO)
            }
            var t, n, r;
            return t = e, r = [{
                key: "version",
                get: function() {
                    return "2.2.2"
                }
            }], (n = [{
                key: "deviceId",
                value: function() {
                    return K.deviceId()
                }
            }, {
                key: "_device",
                get: function() {
                    return this._deviceInstance || (this._deviceInstance = new K), this._deviceInstance
                }
            }, {
                key: "currentSession",
                get: function() {
                    return this._restoreCurrentSession(), this._currentSession
                }
            }, {
                key: "showSessionControls",
                set: function(e) {
                    this._showSessionControls = e, ue.hide(), this.currentSession && this.currentSession.isActive() && this._showSessionControls()
                },
                get: function() {
                    return this._showSessionControls ? this._showSessionControls : ue.show
                }
            }, {
                key: "license",
                set: function(e) {
                    if (this._started && this.license !== e) throw new Error("Cannot change license once Cobrowse is started");
                    v.setHeader("X-CobrowseLicense", e)
                },
                get: function() {
                    return v.getHeader("X-CobrowseLicense")
                }
            }, {
                key: "api",
                set: function(e) {
                    if (this._started && v.api !== e) throw new Error("Cannot change API once Cobrowse is started");
                    v.api = e
                }
            }, {
                key: "customData",
                set: function(e) {
                    this._device.customData = e, this._device.hasPendingUpdates() && this._started && this._updateCustomData()
                }
            }, {
                key: "trustedOrigins",
                set: function(e) {
                    Ne.default.trustedOrigins = e
                }
            }, {
                key: "version",
                get: function() {
                    return e.version
                }
            }]) && Ve(t.prototype, n), r && Ve(t, r), e
        }();
    v.setHeader("Content-Type", "application/json"), v.setHeader("X-CobrowsePlatform", "web"), v.setHeader("X-CobrowseSDKVersion", Ye.version), v.setHeader("X-CobrowseDevice", K.deviceId());
    t.default = new Ye
}, function(e, t, n) {
    "use strict";
    t.a = new function e() {
        var t, n, r;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), r = function(e) {
            return !!e && !(!e.childNodes || "function" != typeof e.appendChild || void 0 === e.nodeType)
        }, (n = "isDOMNode") in (t = this) ? Object.defineProperty(t, n, {
            value: r,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : t[n] = r
    }
}, function(e, t) {
    e.exports = function(e, t) {
        return {
            enumerable: !(1 & e),
            configurable: !(2 & e),
            writable: !(4 & e),
            value: t
        }
    }
}, function(e, t, n) {
    var r = n(16).f,
        o = n(11),
        i = n(4)("toStringTag");
    e.exports = function(e, t, n) {
        e && !o(e = n ? e : e.prototype, i) && r(e, i, {
            configurable: !0,
            value: t
        })
    }
}, function(e, t, n) {
    var r = n(0),
        o = n(9);
    r({
        target: "Object",
        stat: !0,
        forced: !o,
        sham: !o
    }, {
        defineProperty: n(16).f
    })
}, function(e, t) {
    e.exports = function(e, t, n) {
        if (!(e instanceof t)) throw TypeError("Incorrect " + (n ? n + " " : "") + "invocation");
        return e
    }
}, function(e, t, n) {
    var r = n(0),
        o = n(140);
    r({
        target: "Array",
        stat: !0,
        forced: !n(105)((function(e) {
            Array.from(e)
        }))
    }, {
        from: o
    })
}, function(e, t, n) {
    "use strict";
    var r = n(29),
        o = n(17),
        i = n(1),
        a = n(141),
        c = RegExp.prototype,
        s = c.toString,
        u = i((function() {
            return "/a/b" != s.call({
                source: "a",
                flags: "b"
            })
        })),
        f = "toString" != s.name;
    (u || f) && r(RegExp.prototype, "toString", (function() {
        var e = o(this),
            t = String(e.source),
            n = e.flags;
        return "/" + t + "/" + String(void 0 === n && e instanceof RegExp && !("flags" in c) ? a.call(e) : n)
    }), {
        unsafe: !0
    })
}, function(e, t, n) {
    "use strict";
    n.r(t);
    n(14), n(23), n(24), n(87), n(20), n(51), n(5), n(40), n(6), n(52), n(8), n(31), n(12);
    var r = n(25),
        o = n(7),
        i = n(88),
        a = n(81),
        c = n(33);

    function s(e) {
        return (s = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function u(e) {
        return function(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                return n
            }
        }(e) || function(e) {
            if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
        }(e) || function() {
            throw new TypeError("Invalid attempt to spread non-iterable instance")
        }()
    }

    function f(e) {
        return (f = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function l(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function d(e, t) {
        return (d = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function p(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var h = n(37)("cbio.IFrameBridge"),
        v = function(e) {
            function t() {
                var e;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), e = function(e, t) {
                    return !t || "object" !== s(t) && "function" != typeof t ? l(e) : t
                }(this, f(t).call(this)), p(l(e), "trustedOrigins", []), p(l(e), "_isCobrowseMessage", (function(e) {
                    if (!e.data) return !1;
                    var t = e.data,
                        n = t.cobrowseio,
                        r = t.event;
                    return !!n && !!r
                })), p(l(e), "_isTrustedMessage", (function(t) {
                    var n = t.origin;
                    return !!u(e.trustedOrigins).find((function(e) {
                        return e === n
                    }))
                })), p(l(e), "_iframeForMessage", (function(e) {
                    return u(document.getElementsByTagName("iframe")).find((function(t) {
                        return t.contentWindow === e.source
                    }))
                })), p(l(e), "_send", (function(e, t, n) {
                    e.postMessage({
                        cobrowseio: !0,
                        event: t,
                        data: n
                    }, "*")
                })), p(l(e), "_startBroadcast", (function() {
                    e.frameLoop ? (h("restarting broadcast in", document), e.frameLoop.invalidateFrame()) : (h("starting broadcast in", document), e.frameLoop = new i.a((function(t) {
                        return e._send(window.parent, "patch", t), !0
                    })), e.frameLoop.start(), c.a.onChange = function(t) {
                        return e._send(window.parent, "mouse", t)
                    }, c.a.track(window))
                })), p(l(e), "sync", (function(t) {
                    h("iframe available", t, "issuing sync"), e._send(t.contentWindow, "sync")
                })), p(l(e), "enableBroadcast", (function() {
                    h("enable broadcast in", document), e._broadcastEnabled || (e._broadcastEnabled = !0, h("sending start message from", document), e._send(window.parent, "start"), window.addEventListener("message", (function(t) {
                        if (e._isCobrowseMessage(t) && e._isTrustedMessage(t)) {
                            var n = t.data,
                                r = n.event,
                                o = n.data;
                            "sync" === r && e._startBroadcast(), "control" === r && a.a.applyEvent.apply(a.a, u(o))
                        }
                    })))
                })), p(l(e), "enableReceive", (function() {
                    e._receiveEnabled || (e._receiveEnabled = !0, e.on("start", (function(t, n) {
                        h("parent received start from", n), e.sync(n)
                    })), e.on("patch", (function(t, n) {
                        var r = t.document_id,
                            i = n.__vdom;
                        i && i.document.id !== t.document_id && (i = null), i || (i = new o.VirtualDOM(r)), i.applyPatch(t.patch), n.__vdom = i, e.emit("iframe_patched", n)
                    })), e.on("mouse", (function(e, t) {
                        c.a.setPosition(e.x, e.y, t), c.a.setDown(e.down)
                    })))
                })), p(l(e), "broadcastToChildren", (function(t, n) {
                    u(document.getElementsByTagName("iframe")).forEach((function(r) {
                        e._send(r.contentWindow, t, n)
                    }))
                })), h("iframe bridge constructed in", document), window.addEventListener("message", (function(t) {
                    if (e._isCobrowseMessage(t)) {
                        var n = e._iframeForMessage(t);
                        if (n) {
                            var r = t.data,
                                o = r.event,
                                i = r.data;
                            e.emit(o, i, n)
                        }
                    }
                })), e
            }
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && d(e, t)
            }(t, e), t
        }(r.EventEmitter);
    t.default = new v
}, function(e, t) {
    e.exports = function(e) {
        if (null == e) throw TypeError("Can't call method on " + e);
        return e
    }
}, function(e, t) {
    e.exports = !1
}, function(e, t, n) {
    var r = n(118),
        o = n(3),
        i = function(e) {
            return "function" == typeof e ? e : void 0
        };
    e.exports = function(e, t) {
        return arguments.length < 2 ? i(r[e]) || i(o[e]) : r[e] && r[e][t] || o[e] && o[e][t]
    }
}, function(e, t, n) {
    var r = n(42),
        o = Math.max,
        i = Math.min;
    e.exports = function(e, t) {
        var n = r(e);
        return n < 0 ? o(n + t, 0) : i(n, t)
    }
}, function(e, t, n) {
    var r = n(93);
    e.exports = function(e, t, n) {
        if (r(e), void 0 === t) return e;
        switch (n) {
            case 0:
                return function() {
                    return e.call(t)
                };
            case 1:
                return function(n) {
                    return e.call(t, n)
                };
            case 2:
                return function(n, r) {
                    return e.call(t, n, r)
                };
            case 3:
                return function(n, r, o) {
                    return e.call(t, n, r, o)
                }
        }
        return function() {
            return e.apply(t, arguments)
        }
    }
}, function(e, t, n) {
    var r, o = n(17),
        i = n(99),
        a = n(91),
        c = n(67),
        s = n(170),
        u = n(112),
        f = n(83),
        l = f("IE_PROTO"),
        d = function() {},
        p = function(e) {
            return "<script>" + e + "<\/script>"
        },
        h = function() {
            try {
                r = document.domain && new ActiveXObject("htmlfile")
            } catch (e) {}
            var e, t;
            h = r ? function(e) {
                e.write(p("")), e.close();
                var t = e.parentWindow.Object;
                return e = null, t
            }(r) : ((t = u("iframe")).style.display = "none", s.appendChild(t), t.src = String("javascript:"), (e = t.contentWindow.document).open(), e.write(p("document.F=Object")), e.close(), e.F);
            for (var n = a.length; n--;) delete h.prototype[a[n]];
            return h()
        };
    c[l] = !0, e.exports = Object.create || function(e, t) {
        var n;
        return null !== e ? (d.prototype = o(e), n = new d, d.prototype = null, n[l] = e) : n = h(), void 0 === t ? n : i(n, t)
    }
}, function(e, t, n) {
    var r = n(11),
        o = n(22),
        i = n(83),
        a = n(128),
        c = i("IE_PROTO"),
        s = Object.prototype;
    e.exports = a ? Object.getPrototypeOf : function(e) {
        return e = o(e), r(e, c) ? e[c] : "function" == typeof e.constructor && e instanceof e.constructor ? e.constructor.prototype : e instanceof Object ? s : null
    }
}, function(e, t) {
    e.exports = function(e, t) {
        if ("object" == typeof t && null !== t) {
            var n, r = Object.getPrototypeOf(t);
            for (n in r)
                if (!(n in e)) {
                    var o = Object.getOwnPropertyDescriptor(r, n);
                    o && Object.defineProperty(e, n, o)
                } for (n in t) n in e || (e[n] = t[n])
        }
    }
}, function(e, t, n) {
    var r = n(161),
        o = n(97);
    e.exports = function(e, t, n) {
        var i = !0,
            a = !0;
        if ("function" != typeof e) throw new TypeError("Expected a function");
        return o(n) && (i = "leading" in n ? !!n.leading : i, a = "trailing" in n ? !!n.trailing : a), r(e, t, {
            leading: i,
            maxWait: t,
            trailing: a
        })
    }
}, function(e, t, n) {
    var r = n(1),
        o = n(64),
        i = "".split;
    e.exports = r((function() {
        return !Object("z").propertyIsEnumerable(0)
    })) ? function(e) {
        return "String" == o(e) ? i.call(e, "") : Object(e)
    } : Object
}, function(e, t) {
    var n = {}.toString;
    e.exports = function(e) {
        return n.call(e).slice(8, -1)
    }
}, function(e, t, n) {
    var r = n(10);
    e.exports = function(e, t) {
        if (!r(e)) return e;
        var n, o;
        if (t && "function" == typeof(n = e.toString) && !r(o = n.call(e))) return o;
        if ("function" == typeof(n = e.valueOf) && !r(o = n.call(e))) return o;
        if (!t && "function" == typeof(n = e.toString) && !r(o = n.call(e))) return o;
        throw TypeError("Can't convert object to primitive value")
    }
}, function(e, t) {
    var n = 0,
        r = Math.random();
    e.exports = function(e) {
        return "Symbol(" + String(void 0 === e ? "" : e) + ")_" + (++n + r).toString(36)
    }
}, function(e, t) {
    e.exports = {}
}, function(e, t, n) {
    var r = n(119),
        o = n(91).concat("length", "prototype");
    t.f = Object.getOwnPropertyNames || function(e) {
        return r(e, o)
    }
}, function(e, t, n) {
    var r = n(28),
        o = n(13),
        i = n(57),
        a = function(e) {
            return function(t, n, a) {
                var c, s = r(t),
                    u = o(s.length),
                    f = i(a, u);
                if (e && n != n) {
                    for (; u > f;)
                        if ((c = s[f++]) != c) return !0
                } else
                    for (; u > f; f++)
                        if ((e || f in s) && s[f] === n) return e || f || 0;
                return !e && -1
            }
        };
    e.exports = {
        includes: a(!0),
        indexOf: a(!1)
    }
}, function(e, t, n) {
    var r = n(64);
    e.exports = Array.isArray || function(e) {
        return "Array" == r(e)
    }
}, function(e, t, n) {
    "use strict";
    var r = n(1);
    e.exports = function(e, t) {
        var n = [][e];
        return !!n && r((function() {
            n.call(null, t || function() {
                throw 1
            }, 1)
        }))
    }
}, function(e, t, n) {
    var r = n(95),
        o = n(64),
        i = n(4)("toStringTag"),
        a = "Arguments" == o(function() {
            return arguments
        }());
    e.exports = r ? o : function(e) {
        var t, n, r;
        return void 0 === e ? "Undefined" : null === e ? "Null" : "string" == typeof(n = function(e, t) {
            try {
                return e[t]
            } catch (e) {}
        }(t = Object(e), i)) ? n : a ? o(t) : "Object" == (r = o(t)) && "function" == typeof t.callee ? "Arguments" : r
    }
}, function(e, t, n) {
    var r = n(119),
        o = n(91);
    e.exports = Object.keys || function(e) {
        return r(e, o)
    }
}, function(e, t) {
    e.exports = {}
}, function(e, t, n) {
    var r = n(17),
        o = n(171);
    e.exports = Object.setPrototypeOf || ("__proto__" in {} ? function() {
        var e, t = !1,
            n = {};
        try {
            (e = Object.getOwnPropertyDescriptor(Object.prototype, "__proto__").set).call(n, []), t = n instanceof Array
        } catch (e) {}
        return function(n, i) {
            return r(n), o(i), t ? e.call(n, i) : n.__proto__ = i, n
        }
    }() : void 0)
}, function(e, t, n) {
    var r = n(29);
    e.exports = function(e, t, n) {
        for (var o in t) r(e, o, t[o], n);
        return e
    }
}, function(e, t, n) {
    var r = n(72),
        o = n(74),
        i = n(4)("iterator");
    e.exports = function(e) {
        if (null != e) return e[i] || e["@@iterator"] || o[r(e)]
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(21).map,
        i = n(85),
        a = n(39),
        c = i("map"),
        s = a("map");
    r({
        target: "Array",
        proto: !0,
        forced: !c || !s
    }, {
        map: function(e) {
            return o(this, e, arguments.length > 1 ? arguments[1] : void 0)
        }
    })
}, function(e, t, n) {
    "use strict";
    var r = n(102),
        o = n(142);
    e.exports = r("Set", (function(e) {
        return function() {
            return e(this, arguments.length ? arguments[0] : void 0)
        }
    }), o)
}, function(e, t, n) {
    var r = n(17),
        o = n(93),
        i = n(4)("species");
    e.exports = function(e, t) {
        var n, a = r(e).constructor;
        return void 0 === a || null == (n = r(a)[i]) ? t : o(n)
    }
}, function(e, t, n) {
    "use strict";
    n(30), n(197);
    var r = n(15),
        o = n(26);

    function i(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    t.a = new function e() {
        var t = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), i(this, "_applyScroll", (function(e, t) {
            var n = e.defaultView || e;
            n.scrollTo ? n.scrollTo(t.x, t.y) : (n.scrollTop = t.y, n.scrollLeft = t.x)
        })), i(this, "_updateValue", (function(e, t) {
            var n = t.value,
                r = t.checked;
            if (void 0 !== r && "INPUT" === e.tagName && void 0 !== e.checked) {
                var o = Object.getOwnPropertyDescriptor(e.constructor.prototype, "checked");
                o && o.set.call(e, r)
            }
            if (void 0 !== n) {
                var i = Object.getOwnPropertyDescriptor(e.constructor.prototype, "value");
                i && i.set.call(e, n)
            }
        })), i(this, "_generateMouse", (function(e, t) {
            return new MouseEvent(t.state, {
                clientX: t.x,
                clientY: t.y,
                bubbles: !0,
                cancelable: !0,
                view: e.ownerDocument && e.ownerDocument.defaultView || window
            })
        })), i(this, "_generateKeypress", (function(e, t) {
            return new KeyboardEvent(t.state, {
                key: t.key,
                code: t.code,
                ctrlKey: t.ctrlKey,
                shiftKey: t.shiftKey,
                altKey: t.altKey,
                metaKey: t.metaKey,
                charCode: t.charCode,
                keyCode: t.keyCode,
                which: t.which,
                bubbles: !0,
                cancelable: !0,
                view: e.ownerDocument && e.ownerDocument.defaultView || window
            })
        })), i(this, "_generateFocus", (function(e, t) {
            return new FocusEvent(t.state, {
                bubbles: !1,
                cancelable: !1,
                view: e.ownerDocument && e.ownerDocument.defaultView || window
            })
        })), i(this, "_generateChange", (function(e, t) {
            return new CustomEvent(t.state, {
                bubbles: !0,
                cancelable: !1,
                view: e.ownerDocument && e.ownerDocument.defaultView || window
            })
        })), i(this, "_generateInput", (function(e, t) {
            return new CustomEvent(t.state, {
                bubbles: !0,
                cancelable: !1,
                composed: !0,
                view: e.ownerDocument && e.ownerDocument.defaultView || window,
                inputType: t.inputType,
                data: t.data
            })
        })), i(this, "_generateEvent", (function(e, n, r) {
            switch (e) {
                case "keypress":
                    return t._generateKeypress(n, r);
                case "mouse":
                    return t._generateMouse(n, r);
                case "change":
                    return t._generateChange(n, r);
                case "input":
                    return t._generateInput(n, r);
                case "focus":
                    return t._generateFocus(n, r);
                default:
                    return console.warn("CobrowseIO: unknown event type", e, r), null
            }
        })), i(this, "applyEvent", (function(e, n) {
            if (!n.target) return null;
            var i = r.a.getById(n.target.id);
            if (!i) return null;
            if (o.a.isRedacted(i)) return null;
            try {
                if ("scroll" === e) return t._applyScroll(i, n);
                t._updateValue(i, n.target);
                var a = t._generateEvent(e, i, n);
                return a && i.dispatchEvent(a), a
            } catch (t) {
                return console.warn("Error while injecting event", e, t), null
            }
        }))
    }
}, function(e, t, n) {
    "use strict";
    var r = {}.propertyIsEnumerable,
        o = Object.getOwnPropertyDescriptor,
        i = o && !r.call({
            1: 2
        }, 1);
    t.f = i ? function(e) {
        var t = o(this, e);
        return !!t && t.enumerable
    } : r
}, function(e, t, n) {
    var r = n(90),
        o = n(66),
        i = r("keys");
    e.exports = function(e) {
        return i[e] || (i[e] = o(e))
    }
}, function(e, t, n) {
    var r = n(67),
        o = n(10),
        i = n(11),
        a = n(16).f,
        c = n(66),
        s = n(172),
        u = c("meta"),
        f = 0,
        l = Object.isExtensible || function() {
            return !0
        },
        d = function(e) {
            a(e, u, {
                value: {
                    objectID: "O" + ++f,
                    weakData: {}
                }
            })
        },
        p = e.exports = {
            REQUIRED: !1,
            fastKey: function(e, t) {
                if (!o(e)) return "symbol" == typeof e ? e : ("string" == typeof e ? "S" : "P") + e;
                if (!i(e, u)) {
                    if (!l(e)) return "F";
                    if (!t) return "E";
                    d(e)
                }
                return e[u].objectID
            },
            getWeakData: function(e, t) {
                if (!i(e, u)) {
                    if (!l(e)) return !0;
                    if (!t) return !1;
                    d(e)
                }
                return e[u].weakData
            },
            onFreeze: function(e) {
                return s && p.REQUIRED && l(e) && !i(e, u) && d(e), e
            }
        };
    r[u] = !0
}, function(e, t, n) {
    var r = n(1),
        o = n(4),
        i = n(136),
        a = o("species");
    e.exports = function(e) {
        return i >= 51 || !r((function() {
            var t = [];
            return (t.constructor = {})[a] = function() {
                return {
                    foo: 1
                }
            }, 1 !== t[e](Boolean).foo
        }))
    }
}, function(e, t, n) {
    "use strict";
    var r = n(65),
        o = n(16),
        i = n(47);
    e.exports = function(e, t, n) {
        var a = r(t);
        a in e ? o.f(e, a, i(0, n)) : e[a] = n
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(21).find,
        i = n(98),
        a = n(39),
        c = !0,
        s = a("find");
    "find" in [] && Array(1).find((function() {
        c = !1
    })), r({
        target: "Array",
        proto: !0,
        forced: c || !s
    }, {
        find: function(e) {
            return o(this, e, arguments.length > 1 ? arguments[1] : void 0)
        }
    }), i("find")
}, function(e, t, n) {
    "use strict";
    n.d(t, "a", (function() {
        return z
    }));
    var r = n(62),
        o = n.n(r),
        i = (n(14), n(36), n(20), n(78), n(106), n(30), n(43), n(44), n(138), n(31), n(157)),
        a = n.n(i),
        c = n(7);

    function s(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function u(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? s(Object(n), !0).forEach((function(t) {
                f(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : s(Object(n)).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function f(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var l = function e(t) {
            var n = this;
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), f(this, "_difference", (function(e, t) {
                return t ? Object.keys(e).map((function(n) {
                    return !a()(e[n], t[n]) && f({}, n, e[n])
                })).filter((function(e) {
                    return e
                })).reduce((function(e, t) {
                    return u({}, e, {}, t)
                }), {}) : u({}, e)
            })), f(this, "compress", (function() {
                return Object.values(n.modified).map((function(e) {
                    var t = n.state.node(e.id),
                        r = n._difference(e, t),
                        o = u({
                            id: e.id
                        }, r);
                    return 1 !== Object.keys(o).length && o
                })).filter((function(e) {
                    return e
                }))
            })), f(this, "mark", (function() {
                return n.state.applyPatch(Object.values(n.modified)), n.modified = {}, n
            })), f(this, "_flatten", (function(e) {
                return e.childNodes ? u({}, e, {
                    childNodes: e.childNodes.map((function(e) {
                        return {
                            id: e.id
                        }
                    }))
                }) : e
            })), f(this, "push", (function(e) {
                return e && e.modified ? (e.modified.forEach((function(e) {
                    Object(c.depthFirst)(e, (function(e) {
                        n.modified[e.id] = u({}, n.modified[e.id], {}, n._flatten(e))
                    }))
                })), n) : (console.error("invalid patch", e), n)
            })), f(this, "reset", (function(e) {
                return n.state = new c.VirtualDOM(n._id), n.modified = {}, e && n.push({
                    _t: "reset",
                    modified: [e]
                }), n
            })), this._id = t, this.reset()
        },
        d = (n(23), n(24), n(32), n(51), n(5), n(146), n(40), n(6), n(52), n(79), n(8), n(12), n(25)),
        p = n(26),
        h = n(34),
        v = n(15),
        y = n(46),
        g = n(38),
        m = (n(196), n(33));

    function b(e) {
        return (b = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function w(e) {
        return (w = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function _(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function O(e, t) {
        return (O = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function E(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var S = new(function(e) {
        function t() {
            var e;
            return function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, t), e = function(e, t) {
                return !t || "object" !== b(t) && "function" != typeof t ? _(e) : t
            }(this, w(t).call(this)), E(_(e), "windows", new Set), E(_(e), "tap", (function(t) {
                if (!e.windows.has(t)) {
                    e.windows.add(t);
                    var n = _(e),
                        r = t.history.pushState.bind(t.history);
                    t.history.pushState = function() {
                        r.apply(void 0, arguments), n.emit("navigate", t)
                    };
                    var o = t.history.replaceState.bind(t.history);
                    t.history.replaceState = function() {
                        o.apply(void 0, arguments), n.emit("navigate", t)
                    };
                    var i = t.CSSStyleSheet.prototype.insertRule;
                    t.CSSStyleSheet.prototype.insertRule = function() {
                        var e = i.bind(this).apply(void 0, arguments);
                        return n.emit("stylechange", this.ownerNode), e
                    };
                    var a = t.CSSStyleSheet.prototype.deleteRule;
                    t.CSSStyleSheet.prototype.deleteRule = function() {
                        var e = a.bind(this).apply(void 0, arguments);
                        return n.emit("stylechange", this.ownerNode), e
                    }
                }
            })), E(_(e), "untap", (function() {})), e.setMaxListeners(200), e
        }
        return function(e, t) {
            if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
            e.prototype = Object.create(t && t.prototype, {
                constructor: {
                    value: e,
                    writable: !0,
                    configurable: !0
                }
            }), t && O(e, t)
        }(t, e), t
    }(d.EventEmitter));

    function x(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function C(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? x(Object(n), !0).forEach((function(t) {
                j(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : x(Object(n)).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function j(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var A = function e(t) {
        var n = this;
        ! function(e, t) {
            if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
        }(this, e), j(this, "_init", (function() {
            n._propertyWatcher || (n._propertyWatcher = new c.PropertyObserver(n._onPropertySet), S.on("stylechange", n._onModification), S.on("navigate", n._onNavigation))
        })), j(this, "observe", (function(e) {
            if (!n._observing.has(e)) {
                n._init(), e.addEventListener("scroll", n._onEvent, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("focus", n._onFocusChange, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("blur", n._onFocusChange, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("change", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("keydown", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("keyup", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.addEventListener("keypress", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), m.a.track(e), n._observing.add(e);
                var t = (e.ownerDocument || e).defaultView;
                t && !n._windows.has(t) && (n._windows.add(t), S.tap(t), n._propertyWatcher.observe(t.HTMLInputElement, "value"), n._propertyWatcher.observe(t.HTMLInputElement, "checked"), n._propertyWatcher.observe(t.HTMLTextAreaElement, "value"), t.addEventListener && (t.addEventListener("hashchange", n._onEvent, {
                    capture: !0,
                    passive: !0
                }), t.addEventListener("resize", n._onEvent, {
                    capture: !0,
                    passive: !0
                })))
            }
        })), j(this, "disconnect", (function() {
            n._observing.forEach((function(e) {
                e.removeEventListener("scroll", n._onEvent, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("focus", n._onFocusChange, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("blur", n._onFocusChange, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("change", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("keydown", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("keyup", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("keypress", n._onInputEvent, {
                    capture: !0,
                    passive: !0
                }), m.a.untrack(e)
            })), n._observing.clear(), n._windows.forEach((function(e) {
                S.untap(e), e && !Object(c.isxdoc)(e) && e.removeEventListener && (e.removeEventListener("hashchange", n._onEvent, {
                    capture: !0,
                    passive: !0
                }), e.removeEventListener("resize", n._onEvent, {
                    capture: !0,
                    passive: !0
                }))
            })), n._windows.clear(), n._propertyWatcher && (n._propertyWatcher.disconnect(), n._propertyWatcher = null, S.off("stylechange", n._onModification), S.off("navigate", n._onNavigation))
        })), j(this, "_target", (function(e) {
            return e.target && e.target.document || e.target
        })), j(this, "_onEvent", (function(e) {
            var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
            n._onModification(n._target(e), C({}, t, {
                type: e.type
            }))
        })), j(this, "_onNavigation", (function(e) {
            n._onModification(e.document, {
                type: "navigation"
            })
        })), j(this, "_onFocusChange", (function(e) {
            n._onModification(e.target.ownerDocument || e.target, {
                type: e.type
            })
        })), j(this, "_onInputEvent", (function(e) {
            if ("SELECT" === e.target.tagName || "TEXTAREA" === e.target.tagName) n._onModification(n._target(e), {
                recursive: !0,
                type: e.type
            });
            else if ("INPUT" === e.target.tagName) {
                if ("radio" === e.target.type)(e.target.closest("form") || e.target.ownerDocument).querySelectorAll("input[type=radio][name=".concat(e.target.name, "]")).forEach((function(t) {
                    return n._onModification(t, {
                        type: e.type
                    })
                }));
                else n._onModification(n._target(e), {
                    recursive: !0,
                    type: e.type
                })
            }
        })), j(this, "_onPropertySet", (function(e) {
            n._onModification(e, {
                type: "property"
            })
        })), this._onModification = t, this._observing = new Set, this._windows = new Set
    };

    function k(e) {
        return (k = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
            return typeof e
        } : function(e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        })(e)
    }

    function T(e) {
        return function(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                return n
            }
        }(e) || function(e) {
            if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
        }(e) || function() {
            throw new TypeError("Invalid attempt to spread non-iterable instance")
        }()
    }

    function P(e) {
        return (P = Object.setPrototypeOf ? Object.getPrototypeOf : function(e) {
            return e.__proto__ || Object.getPrototypeOf(e)
        })(e)
    }

    function L(e) {
        if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        return e
    }

    function I(e, t) {
        return (I = Object.setPrototypeOf || function(e, t) {
            return e.__proto__ = t, e
        })(e, t)
    }

    function R(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var M = n(37)("cbio.PatchStream"),
        D = function(e) {
            function t(e) {
                var r;
                return function(e, t) {
                    if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
                }(this, t), r = function(e, t) {
                    return !t || "object" !== k(t) && "function" != typeof t ? L(e) : t
                }(this, P(t).call(this)), R(L(r), "start", (function() {
                    M("starting patch stream for", r._root), r._documents = new Set, r._iframeBridge.on("iframe_patched", r._onXDocIframeUpdate), r._canvasUpdateInterval = setInterval(r._updateCanvasContent, 2e3), r._observe(r._root), r.forceUpdate()
                })), R(L(r), "forceUpdate", (function() {
                    r.emit("patch", {
                        _t: "forced",
                        modified: [h.a.depthFirst(r._root, g.a.serialize)]
                    })
                })), R(L(r), "stop", (function() {
                    M("stopping patch stream for", r._root), r._mutationObserver.disconnect(), r._eventObserver.disconnect(), r._iframeBridge.removeListener("iframe_patched", r._onXDocIframeUpdate), clearInterval(r._canvasUpdateInterval), r._documents && r._documents.clear(), r._documents = null
                })), R(L(r), "destroy", (function() {
                    M("destroying patch stream for", r._root), r.stop(), r.removeAllListeners(), h.a.depthFirst(r._root, r._untrack), r._mutationObserver = null, r._eventObserver = null, r._iframeBridge = null, r._root = null
                })), R(L(r), "_updateCanvasContent", (function() {
                    r._documents.forEach((function(e) {
                        Object(c.isxdoc)(e) || e.nodeType !== window.Node.DOCUMENT_FRAGMENT_NODE && [].slice.call(e.getElementsByTagName("canvas")).forEach((function(e) {
                            r.emit("patch", {
                                _t: "canvas",
                                modified: [g.a.serialize(e)]
                            })
                        }))
                    }))
                })), R(L(r), "_track", (function(e) {
                    y.a.isDOMNode(e) && (v.a.track(e), e.nodeType === window.Node.DOCUMENT_NODE && r._observe(e), e.nodeType === window.Node.DOCUMENT_FRAGMENT_NODE && r._observe(e), "IFRAME" === e.tagName && (r._onIframeUpdate({
                        target: e
                    }), e.removeEventListener("load", r._onIframeUpdate), e.addEventListener("load", r._onIframeUpdate)))
                })), R(L(r), "_untrack", (function(e) {
                    v.a.forget(e), "IFRAME" !== e.tagName || Object(c.isxdoc)(e) || e.removeEventListener("load", r._onIframeUpdate)
                })), R(L(r), "_observe", (function(e) {
                    if (e.nodeType !== window.Node.DOCUMENT_NODE && e.nodeType !== window.Node.DOCUMENT_FRAGMENT_NODE) throw new Error("CobrowseIO: observed a non-document or fragment node (type=".concat(e.nodeType, ")"));
                    r._documents.has(e) || (r._documents.add(e), h.a.depthFirst(e, r._track), r._eventObserver.observe(e), r._mutationObserver.observe(e, {
                        attributes: !0,
                        childList: !0,
                        subtree: !0,
                        characterData: !0
                    }))
                })), R(L(r), "_onXDocIframeUpdate", (function(e) {
                    M("xdoc iframe updated", e), p.a.isRedacted(e) || r.emit("patch", {
                        _t: "xdociframeupdate",
                        modified: [h.a.depthFirst(e, g.a.serialize)]
                    })
                })), R(L(r), "_onIframeUpdate", (function(e) {
                    var t = e.target;
                    M("iframe updated", t), p.a.isRedacted(t) || (Object(c.isxdoc)(t) ? r._iframeBridge.sync(t) : r._observe(t.contentWindow.document), r.emit("patch", {
                        _t: "iframeupdate",
                        modified: [h.a.depthFirst(t, g.a.serialize)]
                    }))
                })), R(L(r), "_onEventMutation", (function(e) {
                    var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                    if (r._onDOMChange(r._mutationObserver.takeRecords()), v.a.get(e) && !p.a.isRedacted(e)) {
                        var n = t.recursive ? h.a.depthFirst(e, g.a.serialize) : g.a.serialize(e);
                        r.emit("patch", {
                            _t: "eventmutation:".concat(t.type),
                            modified: [n]
                        })
                    }
                })), R(L(r), "_discoverNodes", (function(e, t, n, r) {
                    r.add(e.target), e.addedNodes && [].slice.call(e.addedNodes).forEach((function(e) {
                        h.a.depthFirst(e, (function(e) {
                            t.add(e), n.delete(e)
                        }))
                    })), e.removedNodes && [].slice.call(e.removedNodes).forEach((function(e) {
                        h.a.depthFirst(e, (function(e) {
                            t.delete(e), r.delete(e), n.add(e)
                        }))
                    }))
                })), R(L(r), "_getModifications", (function(e) {
                    var t = new Set,
                        n = new Set,
                        o = new Set;
                    return e.forEach((function(e) {
                        return r._discoverNodes(e, t, n, o)
                    })), t.forEach((function(e) {
                        return r._track(e)
                    })), n.forEach((function(e) {
                        return r._untrack(e)
                    })), new Set([].concat(T(t), T(o)).filter((function(e) {
                        return !!v.a.get(e)
                    })))
                })), R(L(r), "_onDOMChange", (function(e) {
                    var t = r._getModifications(e);
                    r.emit("patch", {
                        _t: "domchange",
                        modified: T(t).map((function(e) {
                            return g.a.serialize(e)
                        })).filter((function(e) {
                            return e
                        }))
                    })
                })), r._root = e, r._mutationObserver = new MutationObserver(r._onDOMChange), r._eventObserver = new A(r._onEventMutation), r._iframeBridge = n(53).default, r
            }
            return function(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function");
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        writable: !0,
                        configurable: !0
                    }
                }), t && I(e, t)
            }(t, e), t
        }(d.EventEmitter),
        N = n(19);

    function F(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    var U = n(37)("cbio.FrameLoop"),
        z = function e(t) {
            var n = this;
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), F(this, "_compressAndEmitFrame", o()((function() {
                if (n._compression && document && v.a.get(document)) {
                    var e = n._compression.compress();
                    e.length && n._onUpdate({
                        document_id: v.a.get(document),
                        patch: e
                    }) && n._compression.mark()
                }
            }), 110)), F(this, "start", (function() {
                if (U("starting frame loop in", document), !n._compression) throw new Error("FrameLoop was destroyed");
                if (n._sendInterval) throw new Error("Frame loop already started");
                n._patchStream.on("patch", (function(e) {
                    n._compression.push(e), n._compressAndEmitFrame()
                })), n._sendInterval = setInterval((function() {
                    return n._compressAndEmitFrame()
                }), 500), N.a.on("activate", n.invalidateFrame), n._patchStream.start()
            })), F(this, "isRunning", (function() {
                return !!n._sendInterval
            })), F(this, "invalidateFrame", (function() {
                if (U("invalid frame in", document), !n._compression) throw new Error("FrameLoop was destroyed");
                n._compression.reset(), n._patchStream.forceUpdate()
            })), F(this, "destroy", (function() {
                U("destroying frame loop in", document), clearInterval(n._sendInterval), N.a.removeListener("activate", n.invalidateFrame), n._patchStream.destroy(), n._patchStream = null, n._compression.reset(), n._compression = null, n._onUpdate = null
            })), U("frame loop constructed in", document), this._onUpdate = t, this._compression = new l(v.a.track(document)), this._patchStream = new D(document)
        }
}, function(e, t, n) {
    var r = n(3),
        o = n(27);
    e.exports = function(e, t) {
        try {
            o(r, e, t)
        } catch (n) {
            r[e] = t
        }
        return t
    }
}, function(e, t, n) {
    var r = n(55),
        o = n(114);
    (e.exports = function(e, t) {
        return o[e] || (o[e] = void 0 !== t ? t : {})
    })("versions", []).push({
        version: "3.6.4",
        mode: r ? "pure" : "global",
        copyright: "?? 2020 Denis Pushkarev (zloirock.ru)"
    })
}, function(e, t) {
    e.exports = ["constructor", "hasOwnProperty", "isPrototypeOf", "propertyIsEnumerable", "toLocaleString", "toString", "valueOf"]
}, function(e, t) {
    t.f = Object.getOwnPropertySymbols
}, function(e, t) {
    e.exports = function(e) {
        if ("function" != typeof e) throw TypeError(String(e) + " is not a function");
        return e
    }
}, function(e, t, n) {
    var r = n(1);
    e.exports = !!Object.getOwnPropertySymbols && !r((function() {
        return !String(Symbol())
    }))
}, function(e, t, n) {
    var r = {};
    r[n(4)("toStringTag")] = "z", e.exports = "[object z]" === String(r)
}, function(e, t, n) {
    var r = function(e) {
        "use strict";
        var t = Object.prototype,
            n = t.hasOwnProperty,
            r = "function" == typeof Symbol ? Symbol : {},
            o = r.iterator || "@@iterator",
            i = r.asyncIterator || "@@asyncIterator",
            a = r.toStringTag || "@@toStringTag";

        function c(e, t, n, r) {
            var o = t && t.prototype instanceof f ? t : f,
                i = Object.create(o.prototype),
                a = new O(r || []);
            return i._invoke = function(e, t, n) {
                var r = "suspendedStart";
                return function(o, i) {
                    if ("executing" === r) throw new Error("Generator is already running");
                    if ("completed" === r) {
                        if ("throw" === o) throw i;
                        return S()
                    }
                    for (n.method = o, n.arg = i;;) {
                        var a = n.delegate;
                        if (a) {
                            var c = b(a, n);
                            if (c) {
                                if (c === u) continue;
                                return c
                            }
                        }
                        if ("next" === n.method) n.sent = n._sent = n.arg;
                        else if ("throw" === n.method) {
                            if ("suspendedStart" === r) throw r = "completed", n.arg;
                            n.dispatchException(n.arg)
                        } else "return" === n.method && n.abrupt("return", n.arg);
                        r = "executing";
                        var f = s(e, t, n);
                        if ("normal" === f.type) {
                            if (r = n.done ? "completed" : "suspendedYield", f.arg === u) continue;
                            return {
                                value: f.arg,
                                done: n.done
                            }
                        }
                        "throw" === f.type && (r = "completed", n.method = "throw", n.arg = f.arg)
                    }
                }
            }(e, n, a), i
        }

        function s(e, t, n) {
            try {
                return {
                    type: "normal",
                    arg: e.call(t, n)
                }
            } catch (e) {
                return {
                    type: "throw",
                    arg: e
                }
            }
        }
        e.wrap = c;
        var u = {};

        function f() {}

        function l() {}

        function d() {}
        var p = {};
        p[o] = function() {
            return this
        };
        var h = Object.getPrototypeOf,
            v = h && h(h(E([])));
        v && v !== t && n.call(v, o) && (p = v);
        var y = d.prototype = f.prototype = Object.create(p);

        function g(e) {
            ["next", "throw", "return"].forEach((function(t) {
                e[t] = function(e) {
                    return this._invoke(t, e)
                }
            }))
        }

        function m(e) {
            var t;
            this._invoke = function(r, o) {
                function i() {
                    return new Promise((function(t, i) {
                        ! function t(r, o, i, a) {
                            var c = s(e[r], e, o);
                            if ("throw" !== c.type) {
                                var u = c.arg,
                                    f = u.value;
                                return f && "object" == typeof f && n.call(f, "__await") ? Promise.resolve(f.__await).then((function(e) {
                                    t("next", e, i, a)
                                }), (function(e) {
                                    t("throw", e, i, a)
                                })) : Promise.resolve(f).then((function(e) {
                                    u.value = e, i(u)
                                }), (function(e) {
                                    return t("throw", e, i, a)
                                }))
                            }
                            a(c.arg)
                        }(r, o, t, i)
                    }))
                }
                return t = t ? t.then(i, i) : i()
            }
        }

        function b(e, t) {
            var n = e.iterator[t.method];
            if (void 0 === n) {
                if (t.delegate = null, "throw" === t.method) {
                    if (e.iterator.return && (t.method = "return", t.arg = void 0, b(e, t), "throw" === t.method)) return u;
                    t.method = "throw", t.arg = new TypeError("The iterator does not provide a 'throw' method")
                }
                return u
            }
            var r = s(n, e.iterator, t.arg);
            if ("throw" === r.type) return t.method = "throw", t.arg = r.arg, t.delegate = null, u;
            var o = r.arg;
            return o ? o.done ? (t[e.resultName] = o.value, t.next = e.nextLoc, "return" !== t.method && (t.method = "next", t.arg = void 0), t.delegate = null, u) : o : (t.method = "throw", t.arg = new TypeError("iterator result is not an object"), t.delegate = null, u)
        }

        function w(e) {
            var t = {
                tryLoc: e[0]
            };
            1 in e && (t.catchLoc = e[1]), 2 in e && (t.finallyLoc = e[2], t.afterLoc = e[3]), this.tryEntries.push(t)
        }

        function _(e) {
            var t = e.completion || {};
            t.type = "normal", delete t.arg, e.completion = t
        }

        function O(e) {
            this.tryEntries = [{
                tryLoc: "root"
            }], e.forEach(w, this), this.reset(!0)
        }

        function E(e) {
            if (e) {
                var t = e[o];
                if (t) return t.call(e);
                if ("function" == typeof e.next) return e;
                if (!isNaN(e.length)) {
                    var r = -1,
                        i = function t() {
                            for (; ++r < e.length;)
                                if (n.call(e, r)) return t.value = e[r], t.done = !1, t;
                            return t.value = void 0, t.done = !0, t
                        };
                    return i.next = i
                }
            }
            return {
                next: S
            }
        }

        function S() {
            return {
                value: void 0,
                done: !0
            }
        }
        return l.prototype = y.constructor = d, d.constructor = l, d[a] = l.displayName = "GeneratorFunction", e.isGeneratorFunction = function(e) {
            var t = "function" == typeof e && e.constructor;
            return !!t && (t === l || "GeneratorFunction" === (t.displayName || t.name))
        }, e.mark = function(e) {
            return Object.setPrototypeOf ? Object.setPrototypeOf(e, d) : (e.__proto__ = d, a in e || (e[a] = "GeneratorFunction")), e.prototype = Object.create(y), e
        }, e.awrap = function(e) {
            return {
                __await: e
            }
        }, g(m.prototype), m.prototype[i] = function() {
            return this
        }, e.AsyncIterator = m, e.async = function(t, n, r, o) {
            var i = new m(c(t, n, r, o));
            return e.isGeneratorFunction(n) ? i : i.next().then((function(e) {
                return e.done ? e.value : i.next()
            }))
        }, g(y), y[a] = "Generator", y[o] = function() {
            return this
        }, y.toString = function() {
            return "[object Generator]"
        }, e.keys = function(e) {
            var t = [];
            for (var n in e) t.push(n);
            return t.reverse(),
                function n() {
                    for (; t.length;) {
                        var r = t.pop();
                        if (r in e) return n.value = r, n.done = !1, n
                    }
                    return n.done = !0, n
                }
        }, e.values = E, O.prototype = {
            constructor: O,
            reset: function(e) {
                if (this.prev = 0, this.next = 0, this.sent = this._sent = void 0, this.done = !1, this.delegate = null, this.method = "next", this.arg = void 0, this.tryEntries.forEach(_), !e)
                    for (var t in this) "t" === t.charAt(0) && n.call(this, t) && !isNaN(+t.slice(1)) && (this[t] = void 0)
            },
            stop: function() {
                this.done = !0;
                var e = this.tryEntries[0].completion;
                if ("throw" === e.type) throw e.arg;
                return this.rval
            },
            dispatchException: function(e) {
                if (this.done) throw e;
                var t = this;

                function r(n, r) {
                    return a.type = "throw", a.arg = e, t.next = n, r && (t.method = "next", t.arg = void 0), !!r
                }
                for (var o = this.tryEntries.length - 1; o >= 0; --o) {
                    var i = this.tryEntries[o],
                        a = i.completion;
                    if ("root" === i.tryLoc) return r("end");
                    if (i.tryLoc <= this.prev) {
                        var c = n.call(i, "catchLoc"),
                            s = n.call(i, "finallyLoc");
                        if (c && s) {
                            if (this.prev < i.catchLoc) return r(i.catchLoc, !0);
                            if (this.prev < i.finallyLoc) return r(i.finallyLoc)
                        } else if (c) {
                            if (this.prev < i.catchLoc) return r(i.catchLoc, !0)
                        } else {
                            if (!s) throw new Error("try statement without catch or finally");
                            if (this.prev < i.finallyLoc) return r(i.finallyLoc)
                        }
                    }
                }
            },
            abrupt: function(e, t) {
                for (var r = this.tryEntries.length - 1; r >= 0; --r) {
                    var o = this.tryEntries[r];
                    if (o.tryLoc <= this.prev && n.call(o, "finallyLoc") && this.prev < o.finallyLoc) {
                        var i = o;
                        break
                    }
                }
                i && ("break" === e || "continue" === e) && i.tryLoc <= t && t <= i.finallyLoc && (i = null);
                var a = i ? i.completion : {};
                return a.type = e, a.arg = t, i ? (this.method = "next", this.next = i.finallyLoc, u) : this.complete(a)
            },
            complete: function(e, t) {
                if ("throw" === e.type) throw e.arg;
                return "break" === e.type || "continue" === e.type ? this.next = e.arg : "return" === e.type ? (this.rval = this.arg = e.arg, this.method = "return", this.next = "end") : "normal" === e.type && t && (this.next = t), u
            },
            finish: function(e) {
                for (var t = this.tryEntries.length - 1; t >= 0; --t) {
                    var n = this.tryEntries[t];
                    if (n.finallyLoc === e) return this.complete(n.completion, n.afterLoc), _(n), u
                }
            },
            catch: function(e) {
                for (var t = this.tryEntries.length - 1; t >= 0; --t) {
                    var n = this.tryEntries[t];
                    if (n.tryLoc === e) {
                        var r = n.completion;
                        if ("throw" === r.type) {
                            var o = r.arg;
                            _(n)
                        }
                        return o
                    }
                }
                throw new Error("illegal catch attempt")
            },
            delegateYield: function(e, t, n) {
                return this.delegate = {
                    iterator: E(e),
                    resultName: t,
                    nextLoc: n
                }, "next" === this.method && (this.arg = void 0), u
            }
        }, e
    }(e.exports);
    try {
        regeneratorRuntime = r
    } catch (e) {
        Function("r", "regeneratorRuntime = r")(r)
    }
}, function(e, t) {
    e.exports = function(e) {
        var t = typeof e;
        return null != e && ("object" == t || "function" == t)
    }
}, function(e, t, n) {
    var r = n(4),
        o = n(59),
        i = n(16),
        a = r("unscopables"),
        c = Array.prototype;
    null == c[a] && i.f(c, a, {
        configurable: !0,
        value: o(null)
    }), e.exports = function(e) {
        c[a][e] = !0
    }
}, function(e, t, n) {
    var r = n(9),
        o = n(16),
        i = n(17),
        a = n(73);
    e.exports = r ? Object.defineProperties : function(e, t) {
        i(e);
        for (var n, r = a(t), c = r.length, s = 0; c > s;) o.f(e, n = r[s++], t[n]);
        return e
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(126),
        i = n(60),
        a = n(75),
        c = n(48),
        s = n(27),
        u = n(29),
        f = n(4),
        l = n(55),
        d = n(74),
        p = n(127),
        h = p.IteratorPrototype,
        v = p.BUGGY_SAFARI_ITERATORS,
        y = f("iterator"),
        g = function() {
            return this
        };
    e.exports = function(e, t, n, f, p, m, b) {
        o(n, t, f);
        var w, _, O, E = function(e) {
                if (e === p && A) return A;
                if (!v && e in C) return C[e];
                switch (e) {
                    case "keys":
                    case "values":
                    case "entries":
                        return function() {
                            return new n(this, e)
                        }
                }
                return function() {
                    return new n(this)
                }
            },
            S = t + " Iterator",
            x = !1,
            C = e.prototype,
            j = C[y] || C["@@iterator"] || p && C[p],
            A = !v && j || E(p),
            k = "Array" == t && C.entries || j;
        if (k && (w = i(k.call(new e)), h !== Object.prototype && w.next && (l || i(w) === h || (a ? a(w, h) : "function" != typeof w[y] && s(w, y, g)), c(w, S, !0, !0), l && (d[S] = g))), "values" == p && j && "values" !== j.name && (x = !0, A = function() {
                return j.call(this)
            }), l && !b || C[y] === A || s(C, y, A), d[t] = A, p)
            if (_ = {
                    values: E("values"),
                    keys: m ? A : E("keys"),
                    entries: E("entries")
                }, b)
                for (O in _) !v && !x && O in C || u(C, O, _[O]);
            else r({
                target: t,
                proto: !0,
                forced: v || x
            }, _);
        return _
    }
}, function(e, t, n) {
    var r = n(42),
        o = n(54),
        i = function(e) {
            return function(t, n) {
                var i, a, c = String(o(t)),
                    s = r(n),
                    u = c.length;
                return s < 0 || s >= u ? e ? "" : void 0 : (i = c.charCodeAt(s)) < 55296 || i > 56319 || s + 1 === u || (a = c.charCodeAt(s + 1)) < 56320 || a > 57343 ? e ? c.charAt(s) : i : e ? c.slice(s, s + 2) : a - 56320 + (i - 55296 << 10) + 65536
            }
        };
    e.exports = {
        codeAt: i(!1),
        charAt: i(!0)
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(3),
        i = n(120),
        a = n(29),
        c = n(84),
        s = n(103),
        u = n(50),
        f = n(10),
        l = n(1),
        d = n(105),
        p = n(48),
        h = n(131);
    e.exports = function(e, t, n) {
        var v = -1 !== e.indexOf("Map"),
            y = -1 !== e.indexOf("Weak"),
            g = v ? "set" : "add",
            m = o[e],
            b = m && m.prototype,
            w = m,
            _ = {},
            O = function(e) {
                var t = b[e];
                a(b, e, "add" == e ? function(e) {
                    return t.call(this, 0 === e ? 0 : e), this
                } : "delete" == e ? function(e) {
                    return !(y && !f(e)) && t.call(this, 0 === e ? 0 : e)
                } : "get" == e ? function(e) {
                    return y && !f(e) ? void 0 : t.call(this, 0 === e ? 0 : e)
                } : "has" == e ? function(e) {
                    return !(y && !f(e)) && t.call(this, 0 === e ? 0 : e)
                } : function(e, n) {
                    return t.call(this, 0 === e ? 0 : e, n), this
                })
            };
        if (i(e, "function" != typeof m || !(y || b.forEach && !l((function() {
                (new m).entries().next()
            }))))) w = n.getConstructor(t, e, v, g), c.REQUIRED = !0;
        else if (i(e, !0)) {
            var E = new w,
                S = E[g](y ? {} : -0, 1) != E,
                x = l((function() {
                    E.has(1)
                })),
                C = d((function(e) {
                    new m(e)
                })),
                j = !y && l((function() {
                    for (var e = new m, t = 5; t--;) e[g](t, t);
                    return !e.has(-0)
                }));
            C || ((w = t((function(t, n) {
                u(t, w, e);
                var r = h(new m, t, w);
                return null != n && s(n, r[g], r, v), r
            }))).prototype = b, b.constructor = w), (x || j) && (O("delete"), O("has"), v && O("get")), (j || S) && O(g), y && b.clear && delete b.clear
        }
        return _[e] = w, r({
            global: !0,
            forced: w != m
        }, _), p(w, e), y || n.setStrong(w, e, v), w
    }
}, function(e, t, n) {
    var r = n(17),
        o = n(104),
        i = n(13),
        a = n(58),
        c = n(77),
        s = n(130),
        u = function(e, t) {
            this.stopped = e, this.result = t
        };
    (e.exports = function(e, t, n, f, l) {
        var d, p, h, v, y, g, m, b = a(t, n, f ? 2 : 1);
        if (l) d = e;
        else {
            if ("function" != typeof(p = c(e))) throw TypeError("Target is not iterable");
            if (o(p)) {
                for (h = 0, v = i(e.length); v > h; h++)
                    if ((y = f ? b(r(m = e[h])[0], m[1]) : b(e[h])) && y instanceof u) return y;
                return new u(!1)
            }
            d = p.call(e)
        }
        for (g = d.next; !(m = g.call(d)).done;)
            if ("object" == typeof(y = s(d, b, m.value, f)) && y && y instanceof u) return y;
        return new u(!1)
    }).stop = function(e) {
        return new u(!0, e)
    }
}, function(e, t, n) {
    var r = n(4),
        o = n(74),
        i = r("iterator"),
        a = Array.prototype;
    e.exports = function(e) {
        return void 0 !== e && (o.Array === e || a[i] === e)
    }
}, function(e, t, n) {
    var r = n(4)("iterator"),
        o = !1;
    try {
        var i = 0,
            a = {
                next: function() {
                    return {
                        done: !!i++
                    }
                },
                return: function() {
                    o = !0
                }
            };
        a[r] = function() {
            return this
        }, Array.from(a, (function() {
            throw 2
        }))
    } catch (e) {}
    e.exports = function(e, t) {
        if (!t && !o) return !1;
        var n = !1;
        try {
            var i = {};
            i[r] = function() {
                return {
                    next: function() {
                        return {
                            done: n = !0
                        }
                    }
                }
            }, e(i)
        } catch (e) {}
        return n
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(107).left,
        i = n(71),
        a = n(39),
        c = i("reduce"),
        s = a("reduce", {
            1: 0
        });
    r({
        target: "Array",
        proto: !0,
        forced: !c || !s
    }, {
        reduce: function(e) {
            return o(this, e, arguments.length, arguments.length > 1 ? arguments[1] : void 0)
        }
    })
}, function(e, t, n) {
    var r = n(93),
        o = n(22),
        i = n(63),
        a = n(13),
        c = function(e) {
            return function(t, n, c, s) {
                r(n);
                var u = o(t),
                    f = i(u),
                    l = a(u.length),
                    d = e ? l - 1 : 0,
                    p = e ? -1 : 1;
                if (c < 2)
                    for (;;) {
                        if (d in f) {
                            s = f[d], d += p;
                            break
                        }
                        if (d += p, e ? d < 0 : l <= d) throw TypeError("Reduce of empty array with no initial value")
                    }
                for (; e ? d >= 0 : l > d; d += p) d in f && (s = n(s, f[d], d, u));
                return s
            }
        };
    e.exports = {
        left: c(!1),
        right: c(!0)
    }
}, function(e, t, n) {
    "use strict";
    var r, o, i = n(141),
        a = n(185),
        c = RegExp.prototype.exec,
        s = String.prototype.replace,
        u = c,
        f = (r = /a/, o = /b*/g, c.call(r, "a"), c.call(o, "a"), 0 !== r.lastIndex || 0 !== o.lastIndex),
        l = a.UNSUPPORTED_Y || a.BROKEN_CARET,
        d = void 0 !== /()??/.exec("")[1];
    (f || d || l) && (u = function(e) {
        var t, n, r, o, a = this,
            u = l && a.sticky,
            p = i.call(a),
            h = a.source,
            v = 0,
            y = e;
        return u && (-1 === (p = p.replace("y", "")).indexOf("g") && (p += "g"), y = String(e).slice(a.lastIndex), a.lastIndex > 0 && (!a.multiline || a.multiline && "\n" !== e[a.lastIndex - 1]) && (h = "(?: " + h + ")", y = " " + y, v++), n = new RegExp("^(?:" + h + ")", p)), d && (n = new RegExp("^" + h + "$(?!\\s)", p)), f && (t = a.lastIndex), r = c.call(u ? n : a, y), u ? r ? (r.input = r.input.slice(v), r[0] = r[0].slice(v), r.index = a.lastIndex, a.lastIndex += r[0].length) : a.lastIndex = 0 : f && r && (a.lastIndex = a.global ? r.index + r[0].length : t), d && r && r.length > 1 && s.call(r[0], n, (function() {
            for (o = 1; o < arguments.length - 2; o++) void 0 === arguments[o] && (r[o] = void 0)
        })), r
    }), e.exports = u
}, function(e, t, n) {
    var r, o;
    ! function(i, a) {
        "use strict";
        void 0 === (o = "function" == typeof(r = {
            encode: function(e, t) {
                var n, r = new ArrayBuffer(256),
                    o = new DataView(r),
                    i = new Uint8Array(r),
                    a = 0;

                function c(e) {
                    for (var t = r.byteLength, c = a + e; t < c;) t <<= 1;
                    if (t !== r.byteLength) {
                        var s = o;
                        r = new ArrayBuffer(t), o = new DataView(r), i = new Uint8Array(r);
                        for (var u = a + 3 >> 2, f = 0; f < u; ++f) o.setUint32(f << 2, s.getUint32(f << 2))
                    }
                    return n = e, o
                }

                function s() {
                    a += n
                }

                function u(e) {
                    s(c(1).setUint8(a, e))
                }

                function f(e) {
                    c(e.length), i.set(e, a), s()
                }

                function l(e, t) {
                    t < 24 ? u(e << 5 | t) : t < 256 ? (u(e << 5 | 24), u(t)) : t < 65536 ? (u(e << 5 | 25), function(e) {
                        s(c(2).setUint16(a, e))
                    }(t)) : t < 4294967296 ? (u(e << 5 | 26), function(e) {
                        s(c(4).setUint32(a, e))
                    }(t)) : (u(e << 5 | 27), function(e) {
                        var t = e % 4294967296,
                            n = (e - t) / 4294967296,
                            r = c(8);
                        r.setUint32(a, n), r.setUint32(a + 4, t), s()
                    }(t))
                }
                if (function e(n) {
                        var r;
                        if (!1 === n) return u(244);
                        if (!0 === n) return u(245);
                        if (null === n) return u(246);
                        if (void 0 === n) return u(247);
                        switch (typeof n) {
                            case "number":
                                if (Math.floor(n) === n) {
                                    if (0 <= n && n <= 9007199254740992) return l(0, n);
                                    if (-9007199254740992 <= n && n < 0) return l(1, -(n + 1))
                                }
                                return u(251),
                                    function(e) {
                                        s(c(8).setFloat64(a, e))
                                    }(n);
                            case "string":
                                var o = [];
                                for (r = 0; r < n.length; ++r) {
                                    var i = n.charCodeAt(r);
                                    i < 128 ? o.push(i) : i < 2048 ? (o.push(192 | i >> 6), o.push(128 | 63 & i)) : i < 55296 ? (o.push(224 | i >> 12), o.push(128 | i >> 6 & 63), o.push(128 | 63 & i)) : (i = (1023 & i) << 10, i |= 1023 & n.charCodeAt(++r), i += 65536, o.push(240 | i >> 18), o.push(128 | i >> 12 & 63), o.push(128 | i >> 6 & 63), o.push(128 | 63 & i))
                                }
                                return l(3, o.length), f(o);
                            default:
                                var d;
                                if (Array.isArray(n))
                                    for (l(4, d = n.length), r = 0; r < d; ++r) e(n[r]);
                                else if (n instanceof Uint8Array) l(2, n.length), f(n);
                                else {
                                    var p = Object.keys(n);
                                    for (l(5, d = p.length), r = 0; r < d; ++r) {
                                        var h = p[r];
                                        t && (h = parseInt(h)), e(h), e(n[h])
                                    }
                                }
                        }
                    }(e), "slice" in r) return r.slice(0, a);
                for (var d = new ArrayBuffer(a), p = new DataView(d), h = 0; h < a; ++h) p.setUint8(h, o.getUint8(h));
                return d
            },
            decode: function(e, t, n) {
                var r = new DataView(e),
                    o = 0;

                function i(e, t) {
                    return o += e, t
                }

                function a(t) {
                    return i(t, new Uint8Array(e, o, t))
                }

                function c() {
                    return i(1, r.getUint8(o))
                }

                function s() {
                    return i(2, r.getUint16(o))
                }

                function u() {
                    return i(4, r.getUint32(o))
                }

                function f() {
                    return 255 === r.getUint8(o) && (o += 1, !0)
                }

                function l(e) {
                    if (e < 24) return e;
                    if (24 === e) return c();
                    if (25 === e) return s();
                    if (26 === e) return u();
                    if (27 === e) return 4294967296 * u() + u();
                    if (31 === e) return -1;
                    throw "Invalid length encoding"
                }

                function d(e) {
                    var t = c();
                    if (255 === t) return -1;
                    var n = l(31 & t);
                    if (n < 0 || t >> 5 !== e) throw "Invalid indefinite length element";
                    return n
                }

                function p(e, t) {
                    for (var n = 0; n < t; ++n) {
                        var r = c();
                        128 & r && (r < 224 ? (r = (31 & r) << 6 | 63 & c(), t -= 1) : r < 240 ? (r = (15 & r) << 12 | (63 & c()) << 6 | 63 & c(), t -= 2) : (r = (15 & r) << 18 | (63 & c()) << 12 | (63 & c()) << 6 | 63 & c(), t -= 3)), r < 65536 ? e.push(r) : (r -= 65536, e.push(55296 | r >> 10), e.push(56320 | 1023 & r))
                    }
                }
                "function" != typeof t && (t = function(e) {
                    return e
                }), "function" != typeof n && (n = function() {});
                var h = function e() {
                    var u, h, v = c(),
                        y = v >> 5,
                        g = 31 & v;
                    if (7 === y) switch (g) {
                        case 25:
                            return function() {
                                var e = new ArrayBuffer(4),
                                    t = new DataView(e),
                                    n = s(),
                                    r = 32768 & n,
                                    o = 31744 & n,
                                    i = 1023 & n;
                                if (31744 === o) o = 261120;
                                else if (0 !== o) o += 114688;
                                else if (0 !== i) return (r ? -1 : 1) * i * 5.960464477539063e-8;
                                return t.setUint32(0, r << 16 | o << 13 | i << 13), t.getFloat32(0)
                            }();
                        case 26:
                            return i(4, r.getFloat32(o));
                        case 27:
                            return i(8, r.getFloat64(o))
                    }
                    if ((h = l(g)) < 0 && (y < 2 || 6 < y)) throw "Invalid length";
                    switch (y) {
                        case 0:
                            return h;
                        case 1:
                            return -1 - h;
                        case 2:
                            if (h < 0) {
                                for (var m = [], b = 0;
                                    (h = d(y)) >= 0;) b += h, m.push(a(h));
                                var w = new Uint8Array(b),
                                    _ = 0;
                                for (u = 0; u < m.length; ++u) w.set(m[u], _), _ += m[u].length;
                                return w
                            }
                            return a(h);
                        case 3:
                            var O = [];
                            if (h < 0)
                                for (;
                                    (h = d(y)) >= 0;) p(O, h);
                            else p(O, h);
                            var E = "";
                            for (u = 0; u < O.length; u += 8192) E += String.fromCharCode.apply(null, O.slice(u, u + 8192));
                            return E;
                        case 4:
                            var S;
                            if (h < 0)
                                for (S = []; !f();) S.push(e());
                            else
                                for (S = new Array(h), u = 0; u < h; ++u) S[u] = e();
                            return S;
                        case 5:
                            var x = {};
                            for (u = 0; u < h || h < 0 && !f(); ++u) {
                                x[e()] = e()
                            }
                            return x;
                        case 6:
                            return t(e(), h);
                        case 7:
                            switch (h) {
                                case 20:
                                    return !1;
                                case 21:
                                    return !0;
                                case 22:
                                    return null;
                                case 23:
                                    return;
                                default:
                                    return n(h)
                            }
                    }
                }();
                if (o !== e.byteLength) throw "Remaining bytes";
                return h
            }
        }) ? r.call(t, n, t, e) : r) || (e.exports = o)
    }()
}, function(e, t) {
    var n;
    n = function() {
        return this
    }();
    try {
        n = n || new Function("return this")()
    } catch (e) {
        "object" == typeof window && (n = window)
    }
    e.exports = n
}, function(e, t, n) {
    var r = n(9),
        o = n(1),
        i = n(112);
    e.exports = !r && !o((function() {
        return 7 != Object.defineProperty(i("div"), "a", {
            get: function() {
                return 7
            }
        }).a
    }))
}, function(e, t, n) {
    var r = n(3),
        o = n(10),
        i = r.document,
        a = o(i) && o(i.createElement);
    e.exports = function(e) {
        return a ? i.createElement(e) : {}
    }
}, function(e, t, n) {
    var r = n(114),
        o = Function.toString;
    "function" != typeof r.inspectSource && (r.inspectSource = function(e) {
        return o.call(e)
    }), e.exports = r.inspectSource
}, function(e, t, n) {
    var r = n(3),
        o = n(89),
        i = r["__core-js_shared__"] || o("__core-js_shared__", {});
    e.exports = i
}, function(e, t, n) {
    var r = n(3),
        o = n(113),
        i = r.WeakMap;
    e.exports = "function" == typeof i && /native code/.test(o(i))
}, function(e, t, n) {
    var r = n(11),
        o = n(117),
        i = n(41),
        a = n(16);
    e.exports = function(e, t) {
        for (var n = o(t), c = a.f, s = i.f, u = 0; u < n.length; u++) {
            var f = n[u];
            r(e, f) || c(e, f, s(t, f))
        }
    }
}, function(e, t, n) {
    var r = n(56),
        o = n(68),
        i = n(92),
        a = n(17);
    e.exports = r("Reflect", "ownKeys") || function(e) {
        var t = o.f(a(e)),
            n = i.f;
        return n ? t.concat(n(e)) : t
    }
}, function(e, t, n) {
    var r = n(3);
    e.exports = r
}, function(e, t, n) {
    var r = n(11),
        o = n(28),
        i = n(69).indexOf,
        a = n(67);
    e.exports = function(e, t) {
        var n, c = o(e),
            s = 0,
            u = [];
        for (n in c) !r(a, n) && r(c, n) && u.push(n);
        for (; t.length > s;) r(c, n = t[s++]) && (~i(u, n) || u.push(n));
        return u
    }
}, function(e, t, n) {
    var r = n(1),
        o = /#|\.prototype\./,
        i = function(e, t) {
            var n = c[a(e)];
            return n == u || n != s && ("function" == typeof t ? r(t) : !!t)
        },
        a = i.normalize = function(e) {
            return String(e).replace(o, ".").toLowerCase()
        },
        c = i.data = {},
        s = i.NATIVE = "N",
        u = i.POLYFILL = "P";
    e.exports = i
}, function(e, t, n) {
    "use strict";
    var r = n(21).forEach,
        o = n(71),
        i = n(39),
        a = o("forEach"),
        c = i("forEach");
    e.exports = a && c ? [].forEach : function(e) {
        return r(this, e, arguments.length > 1 ? arguments[1] : void 0)
    }
}, function(e, t, n) {
    var r = n(10),
        o = n(70),
        i = n(4)("species");
    e.exports = function(e, t) {
        var n;
        return o(e) && ("function" != typeof(n = e.constructor) || n !== Array && !o(n.prototype) ? r(n) && null === (n = n[i]) && (n = void 0) : n = void 0), new(void 0 === n ? Array : n)(0 === t ? 0 : t)
    }
}, function(e, t, n) {
    var r = n(94);
    e.exports = r && !Symbol.sham && "symbol" == typeof Symbol.iterator
}, function(e, t, n) {
    var r = n(163),
        o = "object" == typeof self && self && self.Object === Object && self,
        i = r || o || Function("return this")();
    e.exports = i
}, function(e, t, n) {
    var r = n(124).Symbol;
    e.exports = r
}, function(e, t, n) {
    "use strict";
    var r = n(127).IteratorPrototype,
        o = n(59),
        i = n(47),
        a = n(48),
        c = n(74),
        s = function() {
            return this
        };
    e.exports = function(e, t, n) {
        var u = t + " Iterator";
        return e.prototype = o(r, {
            next: i(1, n)
        }), a(e, u, !1, !0), c[u] = s, e
    }
}, function(e, t, n) {
    "use strict";
    var r, o, i, a = n(60),
        c = n(27),
        s = n(11),
        u = n(4),
        f = n(55),
        l = u("iterator"),
        d = !1;
    [].keys && ("next" in (i = [].keys()) ? (o = a(a(i))) !== Object.prototype && (r = o) : d = !0), null == r && (r = {}), f || s(r, l) || c(r, l, (function() {
        return this
    })), e.exports = {
        IteratorPrototype: r,
        BUGGY_SAFARI_ITERATORS: d
    }
}, function(e, t, n) {
    var r = n(1);
    e.exports = !r((function() {
        function e() {}
        return e.prototype.constructor = null, Object.getPrototypeOf(new e) !== e.prototype
    }))
}, function(e, t, n) {
    "use strict";
    var r, o = n(3),
        i = n(76),
        a = n(84),
        c = n(102),
        s = n(173),
        u = n(10),
        f = n(35).enforce,
        l = n(115),
        d = !o.ActiveXObject && "ActiveXObject" in o,
        p = Object.isExtensible,
        h = function(e) {
            return function() {
                return e(this, arguments.length ? arguments[0] : void 0)
            }
        },
        v = e.exports = c("WeakMap", h, s);
    if (l && d) {
        r = s.getConstructor(h, "WeakMap", !0), a.REQUIRED = !0;
        var y = v.prototype,
            g = y.delete,
            m = y.has,
            b = y.get,
            w = y.set;
        i(y, {
            delete: function(e) {
                if (u(e) && !p(e)) {
                    var t = f(this);
                    return t.frozen || (t.frozen = new r), g.call(this, e) || t.frozen.delete(e)
                }
                return g.call(this, e)
            },
            has: function(e) {
                if (u(e) && !p(e)) {
                    var t = f(this);
                    return t.frozen || (t.frozen = new r), m.call(this, e) || t.frozen.has(e)
                }
                return m.call(this, e)
            },
            get: function(e) {
                if (u(e) && !p(e)) {
                    var t = f(this);
                    return t.frozen || (t.frozen = new r), m.call(this, e) ? b.call(this, e) : t.frozen.get(e)
                }
                return b.call(this, e)
            },
            set: function(e, t) {
                if (u(e) && !p(e)) {
                    var n = f(this);
                    n.frozen || (n.frozen = new r), m.call(this, e) ? w.call(this, e, t) : n.frozen.set(e, t)
                } else w.call(this, e, t);
                return this
            }
        })
    }
}, function(e, t, n) {
    var r = n(17);
    e.exports = function(e, t, n, o) {
        try {
            return o ? t(r(n)[0], n[1]) : t(n)
        } catch (t) {
            var i = e.return;
            throw void 0 !== i && r(i.call(e)), t
        }
    }
}, function(e, t, n) {
    var r = n(10),
        o = n(75);
    e.exports = function(e, t, n) {
        var i, a;
        return o && "function" == typeof(i = t.constructor) && i !== n && r(a = i.prototype) && a !== n.prototype && o(e, a), e
    }
}, function(e, t) {
    e.exports = {
        CSSRuleList: 0,
        CSSStyleDeclaration: 0,
        CSSValueList: 0,
        ClientRectList: 0,
        DOMRectList: 0,
        DOMStringList: 0,
        DOMTokenList: 1,
        DataTransferItemList: 0,
        FileList: 0,
        HTMLAllCollection: 0,
        HTMLCollection: 0,
        HTMLFormElement: 0,
        HTMLSelectElement: 0,
        MediaList: 0,
        MimeTypeArray: 0,
        NamedNodeMap: 0,
        NodeList: 1,
        PaintRequestList: 0,
        Plugin: 0,
        PluginArray: 0,
        SVGLengthList: 0,
        SVGNumberList: 0,
        SVGPathSegList: 0,
        SVGPointList: 0,
        SVGStringList: 0,
        SVGTransformList: 0,
        SourceBufferList: 0,
        StyleSheetList: 0,
        TextTrackCueList: 0,
        TextTrackList: 0,
        TouchList: 0
    }
}, function(e, t, n) {
    "use strict";

    function r(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    n(49), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = void 0;
    var o = function() {
        function e(t, n) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this._message = t, this._node = n
        }
        var t, n, o;
        return t = e, (n = [{
            key: "message",
            get: function() {
                return this._message
            }
        }, {
            key: "node",
            get: function() {
                return this._node
            }
        }]) && r(t.prototype, n), o && r(t, o), e
    }();
    t.default = o
}, function(e, t, n) {
    var r = n(4);
    t.f = r
}, function(e, t, n) {
    var r = n(118),
        o = n(11),
        i = n(134),
        a = n(16).f;
    e.exports = function(e) {
        var t = r.Symbol || (r.Symbol = {});
        o(t, e) || a(t, e, {
            value: i.f(e)
        })
    }
}, function(e, t, n) {
    var r, o, i = n(3),
        a = n(176),
        c = i.process,
        s = c && c.versions,
        u = s && s.v8;
    u ? o = (r = u.split("."))[0] + r[1] : a && (!(r = a.match(/Edge\/(\d+)/)) || r[1] >= 74) && (r = a.match(/Chrome\/(\d+)/)) && (o = r[1]), e.exports = o && +o
}, function(e, t, n) {
    var r = n(0),
        o = n(9);
    r({
        target: "Object",
        stat: !0,
        forced: !o,
        sham: !o
    }, {
        defineProperties: n(99)
    })
}, function(e, t, n) {
    var r = n(0),
        o = n(177).values;
    r({
        target: "Object",
        stat: !0
    }, {
        values: function(e) {
            return o(e)
        }
    })
}, function(e, t, n) {
    "use strict";

    function r(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function o(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }
    n(14), n(36), n(20), n(51), n(78), n(137), n(49), n(30), n(43), n(44), n(8), n(31), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = function e(t, n) {
        var i = t.childNodes,
            a = n(t, (function() {
                var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : [];
                i = e
            }));
        return i && i.length ? function(e) {
            for (var t = 1; t < arguments.length; t++) {
                var n = null != arguments[t] ? arguments[t] : {};
                t % 2 ? r(n, !0).forEach((function(t) {
                    o(e, t, n[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : r(n).forEach((function(t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
                }))
            }
            return e
        }({}, a, {
            childNodes: Array.from(i).map((function(t) {
                return e(t, n)
            }))
        }) : a
    }, t.depthFirstPostOrder = function e(t, n) {
        var r = Array.from(t.childNodes || []).map((function(t) {
            return e(t, n)
        }));
        return n(t, r)
    }
}, function(e, t, n) {
    "use strict";
    var r = n(58),
        o = n(22),
        i = n(130),
        a = n(104),
        c = n(13),
        s = n(86),
        u = n(77);
    e.exports = function(e) {
        var t, n, f, l, d, p, h = o(e),
            v = "function" == typeof this ? this : Array,
            y = arguments.length,
            g = y > 1 ? arguments[1] : void 0,
            m = void 0 !== g,
            b = u(h),
            w = 0;
        if (m && (g = r(g, y > 2 ? arguments[2] : void 0, 2)), null == b || v == Array && a(b))
            for (n = new v(t = c(h.length)); t > w; w++) p = m ? g(h[w], w) : h[w], s(n, w, p);
        else
            for (d = (l = b.call(h)).next, n = new v; !(f = d.call(l)).done; w++) p = m ? i(l, g, [f.value, w], !0) : f.value, s(n, w, p);
        return n.length = w, n
    }
}, function(e, t, n) {
    "use strict";
    var r = n(17);
    e.exports = function() {
        var e = r(this),
            t = "";
        return e.global && (t += "g"), e.ignoreCase && (t += "i"), e.multiline && (t += "m"), e.dotAll && (t += "s"), e.unicode && (t += "u"), e.sticky && (t += "y"), t
    }
}, function(e, t, n) {
    "use strict";
    var r = n(16).f,
        o = n(59),
        i = n(76),
        a = n(58),
        c = n(50),
        s = n(103),
        u = n(100),
        f = n(143),
        l = n(9),
        d = n(84).fastKey,
        p = n(35),
        h = p.set,
        v = p.getterFor;
    e.exports = {
        getConstructor: function(e, t, n, u) {
            var f = e((function(e, r) {
                    c(e, f, t), h(e, {
                        type: t,
                        index: o(null),
                        first: void 0,
                        last: void 0,
                        size: 0
                    }), l || (e.size = 0), null != r && s(r, e[u], e, n)
                })),
                p = v(t),
                y = function(e, t, n) {
                    var r, o, i = p(e),
                        a = g(e, t);
                    return a ? a.value = n : (i.last = a = {
                        index: o = d(t, !0),
                        key: t,
                        value: n,
                        previous: r = i.last,
                        next: void 0,
                        removed: !1
                    }, i.first || (i.first = a), r && (r.next = a), l ? i.size++ : e.size++, "F" !== o && (i.index[o] = a)), e
                },
                g = function(e, t) {
                    var n, r = p(e),
                        o = d(t);
                    if ("F" !== o) return r.index[o];
                    for (n = r.first; n; n = n.next)
                        if (n.key == t) return n
                };
            return i(f.prototype, {
                clear: function() {
                    for (var e = p(this), t = e.index, n = e.first; n;) n.removed = !0, n.previous && (n.previous = n.previous.next = void 0), delete t[n.index], n = n.next;
                    e.first = e.last = void 0, l ? e.size = 0 : this.size = 0
                },
                delete: function(e) {
                    var t = p(this),
                        n = g(this, e);
                    if (n) {
                        var r = n.next,
                            o = n.previous;
                        delete t.index[n.index], n.removed = !0, o && (o.next = r), r && (r.previous = o), t.first == n && (t.first = r), t.last == n && (t.last = o), l ? t.size-- : this.size--
                    }
                    return !!n
                },
                forEach: function(e) {
                    for (var t, n = p(this), r = a(e, arguments.length > 1 ? arguments[1] : void 0, 3); t = t ? t.next : n.first;)
                        for (r(t.value, t.key, this); t && t.removed;) t = t.previous
                },
                has: function(e) {
                    return !!g(this, e)
                }
            }), i(f.prototype, n ? {
                get: function(e) {
                    var t = g(this, e);
                    return t && t.value
                },
                set: function(e, t) {
                    return y(this, 0 === e ? 0 : e, t)
                }
            } : {
                add: function(e) {
                    return y(this, e = 0 === e ? 0 : e, e)
                }
            }), l && r(f.prototype, "size", {
                get: function() {
                    return p(this).size
                }
            }), f
        },
        setStrong: function(e, t, n) {
            var r = t + " Iterator",
                o = v(t),
                i = v(r);
            u(e, t, (function(e, t) {
                h(this, {
                    type: r,
                    target: e,
                    state: o(e),
                    kind: t,
                    last: void 0
                })
            }), (function() {
                for (var e = i(this), t = e.kind, n = e.last; n && n.removed;) n = n.previous;
                return e.target && (e.last = n = n ? n.next : e.state.first) ? "keys" == t ? {
                    value: n.key,
                    done: !1
                } : "values" == t ? {
                    value: n.value,
                    done: !1
                } : {
                    value: [n.key, n.value],
                    done: !1
                } : (e.target = void 0, {
                    value: void 0,
                    done: !0
                })
            }), n ? "entries" : "values", !n, !0), f(t)
        }
    }
}, function(e, t, n) {
    "use strict";
    var r = n(56),
        o = n(16),
        i = n(4),
        a = n(9),
        c = i("species");
    e.exports = function(e) {
        var t = r(e),
            n = o.f;
        a && t && !t[c] && n(t, c, {
            configurable: !0,
            get: function() {
                return this
            }
        })
    }
}, function(e, t, n) {
    var r = n(0),
        o = n(10),
        i = n(17),
        a = n(11),
        c = n(41),
        s = n(60);
    r({
        target: "Reflect",
        stat: !0
    }, {
        get: function e(t, n) {
            var r, u, f = arguments.length < 3 ? t : arguments[2];
            return i(t) === f ? t[n] : (r = c.f(t, n)) ? a(r, "value") ? r.value : void 0 === r.get ? void 0 : r.get.call(f) : o(u = s(t)) ? e(u, n, f) : void 0
        }
    })
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(108);
    r({
        target: "RegExp",
        proto: !0,
        forced: /./.exec !== o
    }, {
        exec: o
    })
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(10),
        i = n(70),
        a = n(57),
        c = n(13),
        s = n(28),
        u = n(86),
        f = n(4),
        l = n(85),
        d = n(39),
        p = l("slice"),
        h = d("slice", {
            ACCESSORS: !0,
            0: 0,
            1: 2
        }),
        v = f("species"),
        y = [].slice,
        g = Math.max;
    r({
        target: "Array",
        proto: !0,
        forced: !p || !h
    }, {
        slice: function(e, t) {
            var n, r, f, l = s(this),
                d = c(l.length),
                p = a(e, d),
                h = a(void 0 === t ? d : t, d);
            if (i(l) && ("function" != typeof(n = l.constructor) || n !== Array && !i(n.prototype) ? o(n) && null === (n = n[v]) && (n = void 0) : n = void 0, n === Array || void 0 === n)) return y.call(l, p, h);
            for (r = new(void 0 === n ? Array : n)(g(h - p, 0)), f = 0; p < h; p++, f++) p in l && u(r, f, l[p]);
            return r.length = f, r
        }
    })
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(69).includes,
        i = n(98);
    r({
        target: "Array",
        proto: !0,
        forced: !n(39)("indexOf", {
            ACCESSORS: !0,
            1: 0
        })
    }, {
        includes: function(e) {
            return o(this, e, arguments.length > 1 ? arguments[1] : void 0)
        }
    }), i("includes")
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(149),
        i = n(54);
    r({
        target: "String",
        proto: !0,
        forced: !n(150)("includes")
    }, {
        includes: function(e) {
            return !!~String(i(this)).indexOf(o(e), arguments.length > 1 ? arguments[1] : void 0)
        }
    })
}, function(e, t, n) {
    var r = n(194);
    e.exports = function(e) {
        if (r(e)) throw TypeError("The method doesn't accept regular expressions");
        return e
    }
}, function(e, t, n) {
    var r = n(4)("match");
    e.exports = function(e) {
        var t = /./;
        try {
            "/./" [e](t)
        } catch (n) {
            try {
                return t[r] = !1, "/./" [e](t)
            } catch (e) {}
        }
        return !1
    }
}, function(e, t, n) {
    "use strict";
    var r = n(3),
        o = n(9),
        i = n(152),
        a = n(27),
        c = n(76),
        s = n(1),
        u = n(50),
        f = n(42),
        l = n(13),
        d = n(153),
        p = n(205),
        h = n(60),
        v = n(75),
        y = n(68).f,
        g = n(16).f,
        m = n(154),
        b = n(48),
        w = n(35),
        _ = w.get,
        O = w.set,
        E = r.ArrayBuffer,
        S = E,
        x = r.DataView,
        C = x && x.prototype,
        j = Object.prototype,
        A = r.RangeError,
        k = p.pack,
        T = p.unpack,
        P = function(e) {
            return [255 & e]
        },
        L = function(e) {
            return [255 & e, e >> 8 & 255]
        },
        I = function(e) {
            return [255 & e, e >> 8 & 255, e >> 16 & 255, e >> 24 & 255]
        },
        R = function(e) {
            return e[3] << 24 | e[2] << 16 | e[1] << 8 | e[0]
        },
        M = function(e) {
            return k(e, 23, 4)
        },
        D = function(e) {
            return k(e, 52, 8)
        },
        N = function(e, t) {
            g(e.prototype, t, {
                get: function() {
                    return _(this)[t]
                }
            })
        },
        F = function(e, t, n, r) {
            var o = d(n),
                i = _(e);
            if (o + t > i.byteLength) throw A("Wrong index");
            var a = _(i.buffer).bytes,
                c = o + i.byteOffset,
                s = a.slice(c, c + t);
            return r ? s : s.reverse()
        },
        U = function(e, t, n, r, o, i) {
            var a = d(n),
                c = _(e);
            if (a + t > c.byteLength) throw A("Wrong index");
            for (var s = _(c.buffer).bytes, u = a + c.byteOffset, f = r(+o), l = 0; l < t; l++) s[u + l] = f[i ? l : t - l - 1]
        };
    if (i) {
        if (!s((function() {
                E(1)
            })) || !s((function() {
                new E(-1)
            })) || s((function() {
                return new E, new E(1.5), new E(NaN), "ArrayBuffer" != E.name
            }))) {
            for (var z, B = (S = function(e) {
                    return u(this, S), new E(d(e))
                }).prototype = E.prototype, V = y(E), W = 0; V.length > W;)(z = V[W++]) in S || a(S, z, E[z]);
            B.constructor = S
        }
        v && h(C) !== j && v(C, j);
        var K = new x(new S(2)),
            q = C.setInt8;
        K.setInt8(0, 2147483648), K.setInt8(1, 2147483649), !K.getInt8(0) && K.getInt8(1) || c(C, {
            setInt8: function(e, t) {
                q.call(this, e, t << 24 >> 24)
            },
            setUint8: function(e, t) {
                q.call(this, e, t << 24 >> 24)
            }
        }, {
            unsafe: !0
        })
    } else S = function(e) {
        u(this, S, "ArrayBuffer");
        var t = d(e);
        O(this, {
            bytes: m.call(new Array(t), 0),
            byteLength: t
        }), o || (this.byteLength = t)
    }, x = function(e, t, n) {
        u(this, x, "DataView"), u(e, S, "DataView");
        var r = _(e).byteLength,
            i = f(t);
        if (i < 0 || i > r) throw A("Wrong offset");
        if (i + (n = void 0 === n ? r - i : l(n)) > r) throw A("Wrong length");
        O(this, {
            buffer: e,
            byteLength: n,
            byteOffset: i
        }), o || (this.buffer = e, this.byteLength = n, this.byteOffset = i)
    }, o && (N(S, "byteLength"), N(x, "buffer"), N(x, "byteLength"), N(x, "byteOffset")), c(x.prototype, {
        getInt8: function(e) {
            return F(this, 1, e)[0] << 24 >> 24
        },
        getUint8: function(e) {
            return F(this, 1, e)[0]
        },
        getInt16: function(e) {
            var t = F(this, 2, e, arguments.length > 1 ? arguments[1] : void 0);
            return (t[1] << 8 | t[0]) << 16 >> 16
        },
        getUint16: function(e) {
            var t = F(this, 2, e, arguments.length > 1 ? arguments[1] : void 0);
            return t[1] << 8 | t[0]
        },
        getInt32: function(e) {
            return R(F(this, 4, e, arguments.length > 1 ? arguments[1] : void 0))
        },
        getUint32: function(e) {
            return R(F(this, 4, e, arguments.length > 1 ? arguments[1] : void 0)) >>> 0
        },
        getFloat32: function(e) {
            return T(F(this, 4, e, arguments.length > 1 ? arguments[1] : void 0), 23)
        },
        getFloat64: function(e) {
            return T(F(this, 8, e, arguments.length > 1 ? arguments[1] : void 0), 52)
        },
        setInt8: function(e, t) {
            U(this, 1, e, P, t)
        },
        setUint8: function(e, t) {
            U(this, 1, e, P, t)
        },
        setInt16: function(e, t) {
            U(this, 2, e, L, t, arguments.length > 2 ? arguments[2] : void 0)
        },
        setUint16: function(e, t) {
            U(this, 2, e, L, t, arguments.length > 2 ? arguments[2] : void 0)
        },
        setInt32: function(e, t) {
            U(this, 4, e, I, t, arguments.length > 2 ? arguments[2] : void 0)
        },
        setUint32: function(e, t) {
            U(this, 4, e, I, t, arguments.length > 2 ? arguments[2] : void 0)
        },
        setFloat32: function(e, t) {
            U(this, 4, e, M, t, arguments.length > 2 ? arguments[2] : void 0)
        },
        setFloat64: function(e, t) {
            U(this, 8, e, D, t, arguments.length > 2 ? arguments[2] : void 0)
        }
    });
    b(S, "ArrayBuffer"), b(x, "DataView"), e.exports = {
        ArrayBuffer: S,
        DataView: x
    }
}, function(e, t) {
    e.exports = "undefined" != typeof ArrayBuffer && "undefined" != typeof DataView
}, function(e, t, n) {
    var r = n(42),
        o = n(13);
    e.exports = function(e) {
        if (void 0 === e) return 0;
        var t = r(e),
            n = o(t);
        if (t !== n) throw RangeError("Wrong length or index");
        return n
    }
}, function(e, t, n) {
    "use strict";
    var r = n(22),
        o = n(57),
        i = n(13);
    e.exports = function(e) {
        for (var t = r(this), n = i(t.length), a = arguments.length, c = o(a > 1 ? arguments[1] : void 0, n), s = a > 2 ? arguments[2] : void 0, u = void 0 === s ? n : o(s, n); u > c;) t[c++] = e;
        return t
    }
}, function(e, t, n) {
    var r = n(209);
    e.exports = function(e, t) {
        var n = r(e);
        if (n % t) throw RangeError("Wrong offset");
        return n
    }
}, function(e, t, n) {
    var r = n(1),
        o = n(4),
        i = n(55),
        a = o("iterator");
    e.exports = !r((function() {
        var e = new URL("b?a=1&b=2&c=3", "http://a"),
            t = e.searchParams,
            n = "";
        return e.pathname = "c%20d", t.forEach((function(e, r) {
            t.delete("b"), n += r + e
        })), i && !e.toJSON || !t.sort || "http://a/c%20d?a=1&c=3" !== e.href || "3" !== t.get("c") || "a=1" !== String(new URLSearchParams("?a=1")) || !t[a] || "a" !== new URL("https://a@b").username || "b" !== new URLSearchParams(new URLSearchParams("a=b")).get("a") || "xn--e1aybc" !== new URL("http://????????").host || "#%D0%B1" !== new URL("http://a#??").hash || "a1c3" !== n || "x" !== new URL("http://x", void 0).host
    }))
}, function(e, t, n) {
    "use strict";
    e.exports = function e(t, n) {
        if (t === n) return !0;
        if (t && n && "object" == typeof t && "object" == typeof n) {
            if (t.constructor !== n.constructor) return !1;
            var r, o, i;
            if (Array.isArray(t)) {
                if ((r = t.length) != n.length) return !1;
                for (o = r; 0 != o--;)
                    if (!e(t[o], n[o])) return !1;
                return !0
            }
            if (t.constructor === RegExp) return t.source === n.source && t.flags === n.flags;
            if (t.valueOf !== Object.prototype.valueOf) return t.valueOf() === n.valueOf();
            if (t.toString !== Object.prototype.toString) return t.toString() === n.toString();
            if ((r = (i = Object.keys(t)).length) !== Object.keys(n).length) return !1;
            for (o = r; 0 != o--;)
                if (!Object.prototype.hasOwnProperty.call(n, i[o])) return !1;
            for (o = r; 0 != o--;) {
                var a = i[o];
                if (!e(t[a], n[a])) return !1
            }
            return !0
        }
        return t != t && n != n
    }
}, function(e, t, n) {
    "use strict";
    n.r(t);
    var r = n(45).default;
    if (window.__cobrowse_io_loaded) throw CobrowseIO = null, new Error("CobrowseIO already loaded. This usually means you included the snippet multiple times.");
    window.__cobrowse_io_loaded = !0, t.default = r
}, , function(e, t, n) {
    "use strict";
    var r = n(95),
        o = n(72);
    e.exports = r ? {}.toString : function() {
        return "[object " + o(this) + "]"
    }
}, function(e, t, n) {
    var r = n(97),
        o = n(162),
        i = n(164),
        a = Math.max,
        c = Math.min;
    e.exports = function(e, t, n) {
        var s, u, f, l, d, p, h = 0,
            v = !1,
            y = !1,
            g = !0;
        if ("function" != typeof e) throw new TypeError("Expected a function");

        function m(t) {
            var n = s,
                r = u;
            return s = u = void 0, h = t, l = e.apply(r, n)
        }

        function b(e) {
            return h = e, d = setTimeout(_, t), v ? m(e) : l
        }

        function w(e) {
            var n = e - p;
            return void 0 === p || n >= t || n < 0 || y && e - h >= f
        }

        function _() {
            var e = o();
            if (w(e)) return O(e);
            d = setTimeout(_, function(e) {
                var n = t - (e - p);
                return y ? c(n, f - (e - h)) : n
            }(e))
        }

        function O(e) {
            return d = void 0, g && s ? m(e) : (s = u = void 0, l)
        }

        function E() {
            var e = o(),
                n = w(e);
            if (s = arguments, u = this, p = e, n) {
                if (void 0 === d) return b(p);
                if (y) return clearTimeout(d), d = setTimeout(_, t), m(p)
            }
            return void 0 === d && (d = setTimeout(_, t)), l
        }
        return t = i(t) || 0, r(n) && (v = !!n.leading, f = (y = "maxWait" in n) ? a(i(n.maxWait) || 0, t) : f, g = "trailing" in n ? !!n.trailing : g), E.cancel = function() {
            void 0 !== d && clearTimeout(d), h = 0, s = p = u = d = void 0
        }, E.flush = function() {
            return void 0 === d ? l : O(o())
        }, E
    }
}, function(e, t, n) {
    var r = n(124);
    e.exports = function() {
        return r.Date.now()
    }
}, function(e, t, n) {
    (function(t) {
        var n = "object" == typeof t && t && t.Object === Object && t;
        e.exports = n
    }).call(this, n(110))
}, function(e, t, n) {
    var r = n(97),
        o = n(165),
        i = /^\s+|\s+$/g,
        a = /^[-+]0x[0-9a-f]+$/i,
        c = /^0b[01]+$/i,
        s = /^0o[0-7]+$/i,
        u = parseInt;
    e.exports = function(e) {
        if ("number" == typeof e) return e;
        if (o(e)) return NaN;
        if (r(e)) {
            var t = "function" == typeof e.valueOf ? e.valueOf() : e;
            e = r(t) ? t + "" : t
        }
        if ("string" != typeof e) return 0 === e ? e : +e;
        e = e.replace(i, "");
        var n = c.test(e);
        return n || s.test(e) ? u(e.slice(2), n ? 2 : 8) : a.test(e) ? NaN : +e
    }
}, function(e, t, n) {
    var r = n(166),
        o = n(169);
    e.exports = function(e) {
        return "symbol" == typeof e || o(e) && "[object Symbol]" == r(e)
    }
}, function(e, t, n) {
    var r = n(125),
        o = n(167),
        i = n(168),
        a = r ? r.toStringTag : void 0;
    e.exports = function(e) {
        return null == e ? void 0 === e ? "[object Undefined]" : "[object Null]" : a && a in Object(e) ? o(e) : i(e)
    }
}, function(e, t, n) {
    var r = n(125),
        o = Object.prototype,
        i = o.hasOwnProperty,
        a = o.toString,
        c = r ? r.toStringTag : void 0;
    e.exports = function(e) {
        var t = i.call(e, c),
            n = e[c];
        try {
            e[c] = void 0;
            var r = !0
        } catch (e) {}
        var o = a.call(e);
        return r && (t ? e[c] = n : delete e[c]), o
    }
}, function(e, t) {
    var n = Object.prototype.toString;
    e.exports = function(e) {
        return n.call(e)
    }
}, function(e, t) {
    e.exports = function(e) {
        return null != e && "object" == typeof e
    }
}, function(e, t, n) {
    var r = n(56);
    e.exports = r("document", "documentElement")
}, function(e, t, n) {
    var r = n(10);
    e.exports = function(e) {
        if (!r(e) && null !== e) throw TypeError("Can't set " + String(e) + " as a prototype");
        return e
    }
}, function(e, t, n) {
    var r = n(1);
    e.exports = !r((function() {
        return Object.isExtensible(Object.preventExtensions({}))
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(76),
        o = n(84).getWeakData,
        i = n(17),
        a = n(10),
        c = n(50),
        s = n(103),
        u = n(21),
        f = n(11),
        l = n(35),
        d = l.set,
        p = l.getterFor,
        h = u.find,
        v = u.findIndex,
        y = 0,
        g = function(e) {
            return e.frozen || (e.frozen = new m)
        },
        m = function() {
            this.entries = []
        },
        b = function(e, t) {
            return h(e.entries, (function(e) {
                return e[0] === t
            }))
        };
    m.prototype = {
        get: function(e) {
            var t = b(this, e);
            if (t) return t[1]
        },
        has: function(e) {
            return !!b(this, e)
        },
        set: function(e, t) {
            var n = b(this, e);
            n ? n[1] = t : this.entries.push([e, t])
        },
        delete: function(e) {
            var t = v(this.entries, (function(t) {
                return t[0] === e
            }));
            return ~t && this.entries.splice(t, 1), !!~t
        }
    }, e.exports = {
        getConstructor: function(e, t, n, u) {
            var l = e((function(e, r) {
                    c(e, l, t), d(e, {
                        type: t,
                        id: y++,
                        frozen: void 0
                    }), null != r && s(r, e[u], e, n)
                })),
                h = p(t),
                v = function(e, t, n) {
                    var r = h(e),
                        a = o(i(t), !0);
                    return !0 === a ? g(r).set(t, n) : a[r.id] = n, e
                };
            return r(l.prototype, {
                delete: function(e) {
                    var t = h(this);
                    if (!a(e)) return !1;
                    var n = o(e);
                    return !0 === n ? g(t).delete(e) : n && f(n, t.id) && delete n[t.id]
                },
                has: function(e) {
                    var t = h(this);
                    if (!a(e)) return !1;
                    var n = o(e);
                    return !0 === n ? g(t).has(e) : n && f(n, t.id)
                }
            }), r(l.prototype, n ? {
                get: function(e) {
                    var t = h(this);
                    if (a(e)) {
                        var n = o(e);
                        return !0 === n ? g(t).get(e) : n ? n[t.id] : void 0
                    }
                },
                set: function(e, t) {
                    return v(this, e, t)
                }
            } : {
                add: function(e) {
                    return v(this, e, !0)
                }
            }), l
        }
    }
}, function(e, t, n) {
    "use strict";
    n(14), n(36), n(20), n(5), n(78), n(106), n(137), n(49), n(30), n(43), n(44), n(6), n(138), n(8), n(129), n(31), n(12), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = void 0;
    var r, o = function(e) {
            if (e && e.__esModule) return e;
            var t = a();
            if (t && t.has(e)) return t.get(e);
            var n = {};
            if (null != e) {
                var r = Object.defineProperty && Object.getOwnPropertyDescriptor;
                for (var o in e)
                    if (Object.prototype.hasOwnProperty.call(e, o)) {
                        var i = r ? Object.getOwnPropertyDescriptor(e, o) : null;
                        i && (i.get || i.set) ? Object.defineProperty(n, o, i) : n[o] = e[o]
                    }
            }
            n.default = e, t && t.set(e, n);
            return n
        }(n(139)),
        i = (r = n(133)) && r.__esModule ? r : {
            default: r
        };

    function a() {
        if ("function" != typeof WeakMap) return null;
        var e = new WeakMap;
        return a = function() {
            return e
        }, e
    }

    function c(e, t) {
        var n = Object.keys(e);
        if (Object.getOwnPropertySymbols) {
            var r = Object.getOwnPropertySymbols(e);
            t && (r = r.filter((function(t) {
                return Object.getOwnPropertyDescriptor(e, t).enumerable
            }))), n.push.apply(n, r)
        }
        return n
    }

    function s(e) {
        for (var t = 1; t < arguments.length; t++) {
            var n = null != arguments[t] ? arguments[t] : {};
            t % 2 ? c(n, !0).forEach((function(t) {
                u(e, t, n[t])
            })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(n)) : c(n).forEach((function(t) {
                Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(n, t))
            }))
        }
        return e
    }

    function u(e, t, n) {
        return t in e ? Object.defineProperty(e, t, {
            value: n,
            enumerable: !0,
            configurable: !0,
            writable: !0
        }) : e[t] = n, e
    }

    function f(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    var l = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this._id = t, this._dom = {
                id: t,
                childNodes: []
            }, this._mapping = {}
        }
        var t, n, r;
        return t = e, r = [{
            key: "createMapping",
            value: function(e) {
                var t = {};
                return (0, o.default)(e, (function(e) {
                    e.id ? t[e.id] = e : console.warn("node missing id", e)
                })), t
            }
        }, {
            key: "applyPatch",
            value: function(e, t) {
                var n = this.createMapping(e),
                    r = {};
                t.forEach((function(e) {
                    if (e.id) {
                        var t = n[e.id] || {};
                        n[e.id] = s({}, t, {}, e), r[e.id] = !0
                    } else console.warn("diff missing id", e)
                })), Object.values(n).forEach((function(e) {
                    e.childNodes = (e.childNodes || []).map((function(t) {
                        var r = t.id || t,
                            o = n[r];
                        if (!o) throw new i.default("denormalisation failed for child ".concat(r), e);
                        return o
                    }))
                }));
                var a = n[e.id],
                    c = (0, o.depthFirstPostOrder)(a, (function(e, t) {
                        if (e.childNodes = t, t.map((function(e) {
                                return r[e.id]
                            })).reduce((function(e, t) {
                                return e || t
                            }), !1)) {
                            r[e.id] = !0;
                            var o = s({}, e);
                            return n[e.id] = o, o
                        }
                        return e
                    }));
                return {
                    dom: c,
                    mapping: this.createMapping(c)
                }
            }
        }], (n = [{
            key: "node",
            value: function(e) {
                return this._mapping[e]
            }
        }, {
            key: "applyPatch",
            value: function(t) {
                var n = e.applyPatch(this._dom, t),
                    r = n.dom,
                    o = n.mapping;
                return this._dom = r, this._mapping = o, this
            }
        }, {
            key: "id",
            get: function() {
                return this._id
            }
        }, {
            key: "document",
            get: function() {
                return this._dom
            }
        }]) && f(t.prototype, n), r && f(t, r), e
    }();
    t.default = l
}, function(e, t, n) {
    var r = n(28),
        o = n(68).f,
        i = {}.toString,
        a = "object" == typeof window && window && Object.getOwnPropertyNames ? Object.getOwnPropertyNames(window) : [];
    e.exports.f = function(e) {
        return a && "[object Window]" == i.call(e) ? function(e) {
            try {
                return o(e)
            } catch (e) {
                return a.slice()
            }
        }(e) : o(r(e))
    }
}, function(e, t, n) {
    var r = n(56);
    e.exports = r("navigator", "userAgent") || ""
}, function(e, t, n) {
    var r = n(9),
        o = n(73),
        i = n(28),
        a = n(82).f,
        c = function(e) {
            return function(t) {
                for (var n, c = i(t), s = o(c), u = s.length, f = 0, l = []; u > f;) n = s[f++], r && !a.call(c, n) || l.push(e ? [n, c[n]] : c[n]);
                return l
            }
        };
    e.exports = {
        entries: c(!0),
        values: c(!1)
    }
}, function(e, t, n) {
    "use strict";

    function r(e) {
        try {
            return !e.document && !1
        } catch (e) {
            return !0
        }
    }
    n(49), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = function(e) {
        if (r(e)) return !0;
        if ("IFRAME" === e.tagName && r(e.contentWindow)) return !0;
        return !1
    }
}, function(e, t, n) {
    "use strict";
    n(14), n(23), n(24), n(32), n(51), n(180), n(5), n(181), n(49), n(6), n(52), n(8), n(12), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = function e(t) {
        var n = [],
            r = t;
        for (; r.parentNode;) n.push(r.parentNode), r = r.parentNode;
        var o = r.defaultView && r.defaultView.frameElement;
        return o ? [].concat(n, [o], function(e) {
            return function(e) {
                if (Array.isArray(e)) {
                    for (var t = 0, n = new Array(e.length); t < e.length; t++) n[t] = e[t];
                    return n
                }
            }(e) || function(e) {
                if (Symbol.iterator in Object(e) || "[object Arguments]" === Object.prototype.toString.call(e)) return Array.from(e)
            }(e) || function() {
                throw new TypeError("Invalid attempt to spread non-iterable instance")
            }()
        }(e(o))) : n
    }
}, function(e, t, n) {
    n(0)({
        target: "Array",
        stat: !0
    }, {
        isArray: n(70)
    })
}, function(e, t, n) {
    var r = n(29),
        o = Date.prototype,
        i = o.toString,
        a = o.getTime;
    new Date(NaN) + "" != "Invalid Date" && r(o, "toString", (function() {
        var e = a.call(this);
        return e == e ? i.call(this) : "Invalid Date"
    }))
}, function(e, t, n) {
    "use strict";

    function r(e, t) {
        for (var n = 0; n < t.length; n++) {
            var r = t[n];
            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
        }
    }
    n(20), n(5), n(49), n(30), n(6), n(79), n(8), n(31), n(12), Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.default = void 0;
    var o = function() {
        function e(t) {
            ! function(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, e), this.onPropertySet = t, this._klasses = new Set
        }
        var t, n, o;
        return t = e, (n = [{
            key: "_trackObserver",
            value: function(e) {
                e.__cbio_observers || (e.__cbio_observers = new Set), e.__cbio_observers.add(this)
            }
        }, {
            key: "_untrackObserver",
            value: function(e) {
                e.__cbio_observers && e.__cbio_observers.delete(this)
            }
        }, {
            key: "_tap",
            value: function(e, t) {
                if (!e["__cbio_override_".concat(t)]) {
                    e["__cbio_override_".concat(t)] = !0;
                    var n = Object.getOwnPropertyDescriptor(e.prototype, t),
                        r = n.set;
                    n.set = function(n) {
                        var o = this;
                        r.call(this, n), e.__cbio_observers && e.__cbio_observers.forEach((function(e) {
                            e.onPropertySet(o, t, n)
                        }))
                    }, Object.defineProperty(e.prototype, t, n)
                }
            }
        }, {
            key: "observe",
            value: function(e, t) {
                this._klasses.add(e), this._trackObserver(e), this._tap(e, t)
            }
        }, {
            key: "disconnect",
            value: function() {
                var e = this;
                this._klasses.forEach((function(t) {
                    return e._untrackObserver(t)
                })), this._klasses.clear()
            }
        }]) && r(t.prototype, n), o && r(t, o), e
    }();
    t.default = o
}, function(e, t, n) {
    var r, o;
    /*!
     * JavaScript Cookie v2.2.1
     * https://github.com/js-cookie/js-cookie
     *
     * Copyright 2006, 2015 Klaus Hartl & Fagner Brack
     * Released under the MIT license
     */
    ! function(i) {
        if (void 0 === (o = "function" == typeof(r = i) ? r.call(t, n, t, e) : r) || (e.exports = o), !0, e.exports = i(), !!0) {
            var a = window.Cookies,
                c = window.Cookies = i();
            c.noConflict = function() {
                return window.Cookies = a, c
            }
        }
    }((function() {
        function e() {
            for (var e = 0, t = {}; e < arguments.length; e++) {
                var n = arguments[e];
                for (var r in n) t[r] = n[r]
            }
            return t
        }

        function t(e) {
            return e.replace(/(%[0-9A-Z]{2})+/g, decodeURIComponent)
        }
        return function n(r) {
            function o() {}

            function i(t, n, i) {
                if ("undefined" != typeof document) {
                    "number" == typeof(i = e({
                        path: "/"
                    }, o.defaults, i)).expires && (i.expires = new Date(1 * new Date + 864e5 * i.expires)), i.expires = i.expires ? i.expires.toUTCString() : "";
                    try {
                        var a = JSON.stringify(n);
                        /^[\{\[]/.test(a) && (n = a)
                    } catch (e) {}
                    n = r.write ? r.write(n, t) : encodeURIComponent(String(n)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g, decodeURIComponent), t = encodeURIComponent(String(t)).replace(/%(23|24|26|2B|5E|60|7C)/g, decodeURIComponent).replace(/[\(\)]/g, escape);
                    var c = "";
                    for (var s in i) i[s] && (c += "; " + s, !0 !== i[s] && (c += "=" + i[s].split(";")[0]));
                    return document.cookie = t + "=" + n + c
                }
            }

            function a(e, n) {
                if ("undefined" != typeof document) {
                    for (var o = {}, i = document.cookie ? document.cookie.split("; ") : [], a = 0; a < i.length; a++) {
                        var c = i[a].split("="),
                            s = c.slice(1).join("=");
                        n || '"' !== s.charAt(0) || (s = s.slice(1, -1));
                        try {
                            var u = t(c[0]);
                            if (s = (r.read || r)(s, u) || t(s), n) try {
                                s = JSON.parse(s)
                            } catch (e) {}
                            if (o[u] = s, e === u) break
                        } catch (e) {}
                    }
                    return e ? o[e] : o
                }
            }
            return o.set = i, o.get = function(e) {
                return a(e, !1)
            }, o.getJSON = function(e) {
                return a(e, !0)
            }, o.remove = function(t, n) {
                i(t, "", e(n, {
                    expires: -1
                }))
            }, o.defaults = {}, o.withConverter = n, o
        }((function() {}))
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(63),
        i = n(28),
        a = n(71),
        c = [].join,
        s = o != Object,
        u = a("join", ",");
    r({
        target: "Array",
        proto: !0,
        forced: s || !u
    }, {
        join: function(e) {
            return c.call(i(this), void 0 === e ? "," : e)
        }
    })
}, function(e, t, n) {
    "use strict";
    var r = n(1);

    function o(e, t) {
        return RegExp(e, t)
    }
    t.UNSUPPORTED_Y = r((function() {
        var e = o("a", "y");
        return e.lastIndex = 2, null != e.exec("abcd")
    })), t.BROKEN_CARET = r((function() {
        var e = o("^r", "gy");
        return e.lastIndex = 2, null != e.exec("str")
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(187),
        o = n(17),
        i = n(22),
        a = n(13),
        c = n(42),
        s = n(54),
        u = n(188),
        f = n(189),
        l = Math.max,
        d = Math.min,
        p = Math.floor,
        h = /\$([$&'`]|\d\d?|<[^>]*>)/g,
        v = /\$([$&'`]|\d\d?)/g;
    r("replace", 2, (function(e, t, n, r) {
        var y = r.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,
            g = r.REPLACE_KEEPS_$0,
            m = y ? "$" : "$0";
        return [function(n, r) {
            var o = s(this),
                i = null == n ? void 0 : n[e];
            return void 0 !== i ? i.call(n, o, r) : t.call(String(o), n, r)
        }, function(e, r) {
            if (!y && g || "string" == typeof r && -1 === r.indexOf(m)) {
                var i = n(t, e, this, r);
                if (i.done) return i.value
            }
            var s = o(e),
                p = String(this),
                h = "function" == typeof r;
            h || (r = String(r));
            var v = s.global;
            if (v) {
                var w = s.unicode;
                s.lastIndex = 0
            }
            for (var _ = [];;) {
                var O = f(s, p);
                if (null === O) break;
                if (_.push(O), !v) break;
                "" === String(O[0]) && (s.lastIndex = u(p, a(s.lastIndex), w))
            }
            for (var E, S = "", x = 0, C = 0; C < _.length; C++) {
                O = _[C];
                for (var j = String(O[0]), A = l(d(c(O.index), p.length), 0), k = [], T = 1; T < O.length; T++) k.push(void 0 === (E = O[T]) ? E : String(E));
                var P = O.groups;
                if (h) {
                    var L = [j].concat(k, A, p);
                    void 0 !== P && L.push(P);
                    var I = String(r.apply(void 0, L))
                } else I = b(j, p, A, k, P, r);
                A >= x && (S += p.slice(x, A) + I, x = A + j.length)
            }
            return S + p.slice(x)
        }];

        function b(e, n, r, o, a, c) {
            var s = r + e.length,
                u = o.length,
                f = v;
            return void 0 !== a && (a = i(a), f = h), t.call(c, f, (function(t, i) {
                var c;
                switch (i.charAt(0)) {
                    case "$":
                        return "$";
                    case "&":
                        return e;
                    case "`":
                        return n.slice(0, r);
                    case "'":
                        return n.slice(s);
                    case "<":
                        c = a[i.slice(1, -1)];
                        break;
                    default:
                        var f = +i;
                        if (0 === f) return t;
                        if (f > u) {
                            var l = p(f / 10);
                            return 0 === l ? t : l <= u ? void 0 === o[l - 1] ? i.charAt(1) : o[l - 1] + i.charAt(1) : t
                        }
                        c = o[f - 1]
                }
                return void 0 === c ? "" : c
            }))
        }
    }))
}, function(e, t, n) {
    "use strict";
    n(145);
    var r = n(29),
        o = n(1),
        i = n(4),
        a = n(108),
        c = n(27),
        s = i("species"),
        u = !o((function() {
            var e = /./;
            return e.exec = function() {
                var e = [];
                return e.groups = {
                    a: "7"
                }, e
            }, "7" !== "".replace(e, "$<a>")
        })),
        f = "$0" === "a".replace(/./, "$0"),
        l = i("replace"),
        d = !!/./ [l] && "" === /./ [l]("a", "$0"),
        p = !o((function() {
            var e = /(?:)/,
                t = e.exec;
            e.exec = function() {
                return t.apply(this, arguments)
            };
            var n = "ab".split(e);
            return 2 !== n.length || "a" !== n[0] || "b" !== n[1]
        }));
    e.exports = function(e, t, n, l) {
        var h = i(e),
            v = !o((function() {
                var t = {};
                return t[h] = function() {
                    return 7
                }, 7 != "" [e](t)
            })),
            y = v && !o((function() {
                var t = !1,
                    n = /a/;
                return "split" === e && ((n = {}).constructor = {}, n.constructor[s] = function() {
                    return n
                }, n.flags = "", n[h] = /./ [h]), n.exec = function() {
                    return t = !0, null
                }, n[h](""), !t
            }));
        if (!v || !y || "replace" === e && (!u || !f || d) || "split" === e && !p) {
            var g = /./ [h],
                m = n(h, "" [e], (function(e, t, n, r, o) {
                    return t.exec === a ? v && !o ? {
                        done: !0,
                        value: g.call(t, n, r)
                    } : {
                        done: !0,
                        value: e.call(n, t, r)
                    } : {
                        done: !1
                    }
                }), {
                    REPLACE_KEEPS_$0: f,
                    REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE: d
                }),
                b = m[0],
                w = m[1];
            r(String.prototype, e, b), r(RegExp.prototype, h, 2 == t ? function(e, t) {
                return w.call(e, this, t)
            } : function(e) {
                return w.call(e, this)
            })
        }
        l && c(RegExp.prototype[h], "sham", !0)
    }
}, function(e, t, n) {
    "use strict";
    var r = n(101).charAt;
    e.exports = function(e, t, n) {
        return t + (n ? r(e, t).length : 1)
    }
}, function(e, t, n) {
    var r = n(64),
        o = n(108);
    e.exports = function(e, t) {
        var n = e.exec;
        if ("function" == typeof n) {
            var i = n.call(e, t);
            if ("object" != typeof i) throw TypeError("RegExp exec method returned something other than an Object or null");
            return i
        }
        if ("RegExp" !== r(e)) throw TypeError("RegExp#exec called on incompatible receiver");
        return o.call(e, t)
    }
}, function(e, t) {
    var n, r, o = e.exports = {};

    function i() {
        throw new Error("setTimeout has not been defined")
    }

    function a() {
        throw new Error("clearTimeout has not been defined")
    }

    function c(e) {
        if (n === setTimeout) return setTimeout(e, 0);
        if ((n === i || !n) && setTimeout) return n = setTimeout, setTimeout(e, 0);
        try {
            return n(e, 0)
        } catch (t) {
            try {
                return n.call(null, e, 0)
            } catch (t) {
                return n.call(this, e, 0)
            }
        }
    }! function() {
        try {
            n = "function" == typeof setTimeout ? setTimeout : i
        } catch (e) {
            n = i
        }
        try {
            r = "function" == typeof clearTimeout ? clearTimeout : a
        } catch (e) {
            r = a
        }
    }();
    var s, u = [],
        f = !1,
        l = -1;

    function d() {
        f && s && (f = !1, s.length ? u = s.concat(u) : l = -1, u.length && p())
    }

    function p() {
        if (!f) {
            var e = c(d);
            f = !0;
            for (var t = u.length; t;) {
                for (s = u, u = []; ++l < t;) s && s[l].run();
                l = -1, t = u.length
            }
            s = null, f = !1,
                function(e) {
                    if (r === clearTimeout) return clearTimeout(e);
                    if ((r === a || !r) && clearTimeout) return r = clearTimeout, clearTimeout(e);
                    try {
                        r(e)
                    } catch (t) {
                        try {
                            return r.call(null, e)
                        } catch (t) {
                            return r.call(this, e)
                        }
                    }
                }(e)
        }
    }

    function h(e, t) {
        this.fun = e, this.array = t
    }

    function v() {}
    o.nextTick = function(e) {
        var t = new Array(arguments.length - 1);
        if (arguments.length > 1)
            for (var n = 1; n < arguments.length; n++) t[n - 1] = arguments[n];
        u.push(new h(e, t)), 1 !== u.length || f || c(p)
    }, h.prototype.run = function() {
        this.fun.apply(null, this.array)
    }, o.title = "browser", o.browser = !0, o.env = {}, o.argv = [], o.version = "", o.versions = {}, o.on = v, o.addListener = v, o.once = v, o.off = v, o.removeListener = v, o.removeAllListeners = v, o.emit = v, o.prependListener = v, o.prependOnceListener = v, o.listeners = function(e) {
        return []
    }, o.binding = function(e) {
        throw new Error("process.binding is not supported")
    }, o.cwd = function() {
        return "/"
    }, o.chdir = function(e) {
        throw new Error("process.chdir is not supported")
    }, o.umask = function() {
        return 0
    }
}, function(e, t, n) {
    "use strict";
    e.exports = function(e) {
        function t(e) {
            for (var t = 0, n = 0; n < e.length; n++) t = (t << 5) - t + e.charCodeAt(n), t |= 0;
            return r.colors[Math.abs(t) % r.colors.length]
        }

        function r(e) {
            var n;

            function a() {
                if (a.enabled) {
                    for (var e = arguments.length, t = new Array(e), o = 0; o < e; o++) t[o] = arguments[o];
                    var i = a,
                        c = Number(new Date),
                        s = c - (n || c);
                    i.diff = s, i.prev = n, i.curr = c, n = c, t[0] = r.coerce(t[0]), "string" != typeof t[0] && t.unshift("%O");
                    var u = 0;
                    t[0] = t[0].replace(/%([a-zA-Z%])/g, (function(e, n) {
                        if ("%%" === e) return e;
                        u++;
                        var o = r.formatters[n];
                        if ("function" == typeof o) {
                            var a = t[u];
                            e = o.call(i, a), t.splice(u, 1), u--
                        }
                        return e
                    })), r.formatArgs.call(i, t);
                    var f = i.log || r.log;
                    f.apply(i, t)
                }
            }
            return a.namespace = e, a.enabled = r.enabled(e), a.useColors = r.useColors(), a.color = t(e), a.destroy = o, a.extend = i, "function" == typeof r.init && r.init(a), r.instances.push(a), a
        }

        function o() {
            var e = r.instances.indexOf(this);
            return -1 !== e && (r.instances.splice(e, 1), !0)
        }

        function i(e, t) {
            return r(this.namespace + (void 0 === t ? ":" : t) + e)
        }
        return r.debug = r, r.default = r, r.coerce = function(e) {
            if (e instanceof Error) return e.stack || e.message;
            return e
        }, r.disable = function() {
            r.enable("")
        }, r.enable = function(e) {
            var t;
            r.save(e), r.names = [], r.skips = [];
            var n = ("string" == typeof e ? e : "").split(/[\s,]+/),
                o = n.length;
            for (t = 0; t < o; t++) n[t] && ("-" === (e = n[t].replace(/\*/g, ".*?"))[0] ? r.skips.push(new RegExp("^" + e.substr(1) + "$")) : r.names.push(new RegExp("^" + e + "$")));
            for (t = 0; t < r.instances.length; t++) {
                var i = r.instances[t];
                i.enabled = r.enabled(i.namespace)
            }
        }, r.enabled = function(e) {
            if ("*" === e[e.length - 1]) return !0;
            var t, n;
            for (t = 0, n = r.skips.length; t < n; t++)
                if (r.skips[t].test(e)) return !1;
            for (t = 0, n = r.names.length; t < n; t++)
                if (r.names[t].test(e)) return !0;
            return !1
        }, r.humanize = n(192), Object.keys(e).forEach((function(t) {
            r[t] = e[t]
        })), r.instances = [], r.names = [], r.skips = [], r.formatters = {}, r.selectColor = t, r.enable(r.load()), r
    }
}, function(e, t) {
    var n = 1e3,
        r = 6e4,
        o = 36e5,
        i = 24 * o;

    function a(e, t, n, r) {
        var o = t >= 1.5 * n;
        return Math.round(e / n) + " " + r + (o ? "s" : "")
    }
    e.exports = function(e, t) {
        t = t || {};
        var c = typeof e;
        if ("string" === c && e.length > 0) return function(e) {
            if ((e = String(e)).length > 100) return;
            var t = /^(-?(?:\d+)?\.?\d+) *(milliseconds?|msecs?|ms|seconds?|secs?|s|minutes?|mins?|m|hours?|hrs?|h|days?|d|weeks?|w|years?|yrs?|y)?$/i.exec(e);
            if (!t) return;
            var a = parseFloat(t[1]);
            switch ((t[2] || "ms").toLowerCase()) {
                case "years":
                case "year":
                case "yrs":
                case "yr":
                case "y":
                    return 315576e5 * a;
                case "weeks":
                case "week":
                case "w":
                    return 6048e5 * a;
                case "days":
                case "day":
                case "d":
                    return a * i;
                case "hours":
                case "hour":
                case "hrs":
                case "hr":
                case "h":
                    return a * o;
                case "minutes":
                case "minute":
                case "mins":
                case "min":
                case "m":
                    return a * r;
                case "seconds":
                case "second":
                case "secs":
                case "sec":
                case "s":
                    return a * n;
                case "milliseconds":
                case "millisecond":
                case "msecs":
                case "msec":
                case "ms":
                    return a;
                default:
                    return
            }
        }(e);
        if ("number" === c && isFinite(e)) return t.long ? function(e) {
            var t = Math.abs(e);
            if (t >= i) return a(e, t, i, "day");
            if (t >= o) return a(e, t, o, "hour");
            if (t >= r) return a(e, t, r, "minute");
            if (t >= n) return a(e, t, n, "second");
            return e + " ms"
        }(e) : function(e) {
            var t = Math.abs(e);
            if (t >= i) return Math.round(e / i) + "d";
            if (t >= o) return Math.round(e / o) + "h";
            if (t >= r) return Math.round(e / r) + "m";
            if (t >= n) return Math.round(e / n) + "s";
            return e + "ms"
        }(e);
        throw new Error("val is not a non-empty string or a valid number. val=" + JSON.stringify(e))
    }
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(69).indexOf,
        i = n(71),
        a = n(39),
        c = [].indexOf,
        s = !!c && 1 / [1].indexOf(1, -0) < 0,
        u = i("indexOf"),
        f = a("indexOf", {
            ACCESSORS: !0,
            1: 0
        });
    r({
        target: "Array",
        proto: !0,
        forced: s || !u || !f
    }, {
        indexOf: function(e) {
            return s ? c.apply(this, arguments) || 0 : o(this, e, arguments.length > 1 ? arguments[1] : void 0)
        }
    })
}, function(e, t, n) {
    var r = n(10),
        o = n(64),
        i = n(4)("match");
    e.exports = function(e) {
        var t;
        return r(e) && (void 0 !== (t = e[i]) ? !!t : "RegExp" == o(e))
    }
}, function(e, t, n) {
    "use strict";
    var r = n(102),
        o = n(142);
    e.exports = r("Map", (function(e) {
        return function() {
            return e(this, arguments.length ? arguments[0] : void 0)
        }
    }), o)
}, function(e, t, n) {
    var r = n(9),
        o = n(16).f,
        i = Function.prototype,
        a = i.toString,
        c = /^\s*function ([^ (]*)/;
    !r || "name" in i || o(i, "name", {
        configurable: !0,
        get: function() {
            try {
                return a.call(this).match(c)[1]
            } catch (e) {
                return ""
            }
        }
    })
}, function(e, t, n) {
    n(198), n(199), n(200), n(201), n(202), n(203)
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.Event("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (r) {
            var t = window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("Event");
                    return r.initEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.detail ? {} : n.detail), e(r, this), r
                };
            n.prototype = t.prototype, window.Event = n
        }
    }(n(61))
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.CustomEvent("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (r) {
            var t = window.CustomEvent || window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("CustomEvent");
                    return r.initCustomEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.detail ? {} : n.detail), e(r, this), r
                };
            n.prototype = t.prototype, window.CustomEvent = n
        }
    }(n(61))
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.MouseEvent("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (r) {
            var t = window.MouseEvent || window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("MouseEvent");
                    return r.initMouseEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.view ? window : n.view, void 0 === n.detail ? 0 : n.detail, void 0 === n.screenX ? 0 : n.screenX, void 0 === n.screenY ? 0 : n.screenY, void 0 === n.clientX ? 0 : n.clientX, void 0 === n.clientY ? 0 : n.clientY, void 0 !== n.ctrlKey && n.ctrlKey, void 0 !== n.altKey && n.altKey, void 0 !== n.shiftKey && n.shiftKey, void 0 !== n.metaKey && n.metaKey, void 0 === n.button ? 0 : n.button, void 0 === n.relatedTarget ? null : n.relatedTarget), r.buttons = void 0 === n.buttons ? 0 : n.buttons, r.region = void 0 === n.region ? null : n.region, e(r, this), r
                };
            n.prototype = t.prototype, window.MouseEvent = n
        }
    }(n(61))
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.KeyboardEvent("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (r) {
            var t = window.KeyboardEvent || window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("KeyboardEvent");
                    return r.initKeyboardEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.view ? window : n.view, void 0 === n.key ? "" : n.key, void 0 === n.location ? 0 : n.location, (!0 === n.ctrlKey ? "Control " : "") + (!0 === n.altKey ? "Alt " : "") + (!0 === n.shiftKey ? "Shift " : "") + (!0 === n.metaKey ? "Meta " : ""), void 0 !== n.repeat && n.repeat, void 0 === n.locale ? navigator.language : n.locale), r.keyCode = void 0 === n.keyCode ? 0 : n.keyCode, r.code = void 0 === n.code ? "" : n.code, r.charCode = void 0 === n.charCode ? 0 : n.charCode, r.char = void 0 === n.charCode ? "" : n.charCode, r.which = void 0 === n.which ? 0 : n.which, e(r, this), r
                };
            n.prototype = t.prototype, window.KeyboardEvent = n
        }
    }(n(61))
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.FocusEvent("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (r) {
            var t = window.FocusEvent || window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("FocusEvent");
                    return r.initFocusEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.view ? window : n.view, void 0 === n.detail ? {} : n.detail, void 0 === n.relatedTarget ? null : n.relatedTarget), e(r, this), r
                };
            n.prototype = t.prototype, window.FocusEvent = n
        }
    }(n(61))
}, function(e, t, n) {
    ! function(e) {
        try {
            new window.PointerEvent("event", {
                bubbles: !0,
                cancelable: !0
            })
        } catch (o) {
            var t = window.PointerEvent || window.Event,
                n = function(t, n) {
                    n = n || {};
                    var r = document.createEvent("PointerEvent");
                    return r.initPointerEvent(t, void 0 !== n.bubbles && n.bubbles, void 0 !== n.cancelable && n.cancelable, void 0 === n.view ? window : n.view, void 0 === n.detail ? 0 : n.detail, void 0 === n.screenX ? 0 : n.screenX, void 0 === n.screenY ? 0 : n.screenY, void 0 === n.clientX ? 0 : n.clientX, void 0 === n.clientY ? 0 : n.clientY, void 0 !== n.ctrlKey && n.ctrlKey, void 0 !== n.altKey && n.altKey, void 0 !== n.shiftKey && n.shiftKey, void 0 !== n.metaKey && n.metaKey, void 0 === n.button ? 0 : n.button, void 0 === n.relatedTarget ? null : n.relatedTarget, void 0 === n.offsetX ? 0 : n.offsetX, void 0 === n.offsetY ? 0 : n.offsetY, void 0 === n.width ? 1 : n.width, void 0 === n.height ? 1 : n.height, void 0 === n.pressure ? 0 : n.pressure, void 0 === n.twist ? 0 : n.twist, void 0 === n.tiltX ? 0 : n.tiltX, void 0 === n.tiltY ? 0 : n.tiltY, void 0 === n.pointerId ? 0 : n.pointerId, void 0 === n.pointerType ? "" : n.pointerType, void 0 === n.hwTimestamp ? 0 : n.hwTimestamp, void 0 !== n.isPrimary && n.isPrimary), r.tangentialPressure = void 0 === n.tangentialPressure ? 0 : n.tangentialPressure, e(r, this), r
                };
            n.prototype = t.prototype;
            var r = Object.getOwnPropertyDescriptor(n.prototype, "rotation");
            r && Object.defineProperty(n.prototype, "twist", r), window.PointerEvent = n
        }
    }(n(61))
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(1),
        i = n(151),
        a = n(17),
        c = n(57),
        s = n(13),
        u = n(80),
        f = i.ArrayBuffer,
        l = i.DataView,
        d = f.prototype.slice;
    r({
        target: "ArrayBuffer",
        proto: !0,
        unsafe: !0,
        forced: o((function() {
            return !new f(2).slice(1, void 0).byteLength
        }))
    }, {
        slice: function(e, t) {
            if (void 0 !== d && void 0 === t) return d.call(a(this), e);
            for (var n = a(this).byteLength, r = c(e, n), o = c(void 0 === t ? n : t, n), i = new(u(this, f))(s(o - r)), p = new l(this), h = new l(i), v = 0; r < o;) h.setUint8(v++, p.getUint8(r++));
            return i
        }
    })
}, function(e, t) {
    var n = Math.abs,
        r = Math.pow,
        o = Math.floor,
        i = Math.log,
        a = Math.LN2;
    e.exports = {
        pack: function(e, t, c) {
            var s, u, f, l = new Array(c),
                d = 8 * c - t - 1,
                p = (1 << d) - 1,
                h = p >> 1,
                v = 23 === t ? r(2, -24) - r(2, -77) : 0,
                y = e < 0 || 0 === e && 1 / e < 0 ? 1 : 0,
                g = 0;
            for ((e = n(e)) != e || e === 1 / 0 ? (u = e != e ? 1 : 0, s = p) : (s = o(i(e) / a), e * (f = r(2, -s)) < 1 && (s--, f *= 2), (e += s + h >= 1 ? v / f : v * r(2, 1 - h)) * f >= 2 && (s++, f /= 2), s + h >= p ? (u = 0, s = p) : s + h >= 1 ? (u = (e * f - 1) * r(2, t), s += h) : (u = e * r(2, h - 1) * r(2, t), s = 0)); t >= 8; l[g++] = 255 & u, u /= 256, t -= 8);
            for (s = s << t | u, d += t; d > 0; l[g++] = 255 & s, s /= 256, d -= 8);
            return l[--g] |= 128 * y, l
        },
        unpack: function(e, t) {
            var n, o = e.length,
                i = 8 * o - t - 1,
                a = (1 << i) - 1,
                c = a >> 1,
                s = i - 7,
                u = o - 1,
                f = e[u--],
                l = 127 & f;
            for (f >>= 7; s > 0; l = 256 * l + e[u], u--, s -= 8);
            for (n = l & (1 << -s) - 1, l >>= -s, s += t; s > 0; n = 256 * n + e[u], u--, s -= 8);
            if (0 === l) l = 1 - c;
            else {
                if (l === a) return n ? NaN : f ? -1 / 0 : 1 / 0;
                n += r(2, t), l -= c
            }
            return (f ? -1 : 1) * n * r(2, l - t)
        }
    }
}, function(e, t, n) {
    n(207)("Uint8", (function(e) {
        return function(t, n, r) {
            return e(this, t, n, r)
        }
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(0),
        o = n(3),
        i = n(9),
        a = n(208),
        c = n(2),
        s = n(151),
        u = n(50),
        f = n(47),
        l = n(27),
        d = n(13),
        p = n(153),
        h = n(155),
        v = n(65),
        y = n(11),
        g = n(72),
        m = n(10),
        b = n(59),
        w = n(75),
        _ = n(68).f,
        O = n(210),
        E = n(21).forEach,
        S = n(143),
        x = n(16),
        C = n(41),
        j = n(35),
        A = n(131),
        k = j.get,
        T = j.set,
        P = x.f,
        L = C.f,
        I = Math.round,
        R = o.RangeError,
        M = s.ArrayBuffer,
        D = s.DataView,
        N = c.NATIVE_ARRAY_BUFFER_VIEWS,
        F = c.TYPED_ARRAY_TAG,
        U = c.TypedArray,
        z = c.TypedArrayPrototype,
        B = c.aTypedArrayConstructor,
        V = c.isTypedArray,
        W = function(e, t) {
            for (var n = 0, r = t.length, o = new(B(e))(r); r > n;) o[n] = t[n++];
            return o
        },
        K = function(e, t) {
            P(e, t, {
                get: function() {
                    return k(this)[t]
                }
            })
        },
        q = function(e) {
            var t;
            return e instanceof M || "ArrayBuffer" == (t = g(e)) || "SharedArrayBuffer" == t
        },
        Y = function(e, t) {
            return V(e) && "symbol" != typeof t && t in e && String(+t) == String(t)
        },
        X = function(e, t) {
            return Y(e, t = v(t, !0)) ? f(2, e[t]) : L(e, t)
        },
        H = function(e, t, n) {
            return !(Y(e, t = v(t, !0)) && m(n) && y(n, "value")) || y(n, "get") || y(n, "set") || n.configurable || y(n, "writable") && !n.writable || y(n, "enumerable") && !n.enumerable ? P(e, t, n) : (e[t] = n.value, e)
        };
    i ? (N || (C.f = X, x.f = H, K(z, "buffer"), K(z, "byteOffset"), K(z, "byteLength"), K(z, "length")), r({
        target: "Object",
        stat: !0,
        forced: !N
    }, {
        getOwnPropertyDescriptor: X,
        defineProperty: H
    }), e.exports = function(e, t, n) {
        var i = e.match(/\d+$/)[0] / 8,
            c = e + (n ? "Clamped" : "") + "Array",
            s = "get" + e,
            f = "set" + e,
            v = o[c],
            y = v,
            g = y && y.prototype,
            x = {},
            C = function(e, t) {
                P(e, t, {
                    get: function() {
                        return function(e, t) {
                            var n = k(e);
                            return n.view[s](t * i + n.byteOffset, !0)
                        }(this, t)
                    },
                    set: function(e) {
                        return function(e, t, r) {
                            var o = k(e);
                            n && (r = (r = I(r)) < 0 ? 0 : r > 255 ? 255 : 255 & r), o.view[f](t * i + o.byteOffset, r, !0)
                        }(this, t, e)
                    },
                    enumerable: !0
                })
            };
        N ? a && (y = t((function(e, t, n, r) {
            return u(e, y, c), A(m(t) ? q(t) ? void 0 !== r ? new v(t, h(n, i), r) : void 0 !== n ? new v(t, h(n, i)) : new v(t) : V(t) ? W(y, t) : O.call(y, t) : new v(p(t)), e, y)
        })), w && w(y, U), E(_(v), (function(e) {
            e in y || l(y, e, v[e])
        })), y.prototype = g) : (y = t((function(e, t, n, r) {
            u(e, y, c);
            var o, a, s, f = 0,
                l = 0;
            if (m(t)) {
                if (!q(t)) return V(t) ? W(y, t) : O.call(y, t);
                o = t, l = h(n, i);
                var v = t.byteLength;
                if (void 0 === r) {
                    if (v % i) throw R("Wrong length");
                    if ((a = v - l) < 0) throw R("Wrong length")
                } else if ((a = d(r) * i) + l > v) throw R("Wrong length");
                s = a / i
            } else s = p(t), o = new M(a = s * i);
            for (T(e, {
                    buffer: o,
                    byteOffset: l,
                    byteLength: a,
                    length: s,
                    view: new D(o)
                }); f < s;) C(e, f++)
        })), w && w(y, U), g = y.prototype = b(z)), g.constructor !== y && l(g, "constructor", y), F && l(g, F, c), x[c] = y, r({
            global: !0,
            forced: y != v,
            sham: !N
        }, x), "BYTES_PER_ELEMENT" in y || l(y, "BYTES_PER_ELEMENT", i), "BYTES_PER_ELEMENT" in g || l(g, "BYTES_PER_ELEMENT", i), S(c)
    }) : e.exports = function() {}
}, function(e, t, n) {
    var r = n(3),
        o = n(1),
        i = n(105),
        a = n(2).NATIVE_ARRAY_BUFFER_VIEWS,
        c = r.ArrayBuffer,
        s = r.Int8Array;
    e.exports = !a || !o((function() {
        s(1)
    })) || !o((function() {
        new s(-1)
    })) || !i((function(e) {
        new s, new s(null), new s(1.5), new s(e)
    }), !0) || o((function() {
        return 1 !== new s(new c(2), 1, void 0).length
    }))
}, function(e, t, n) {
    var r = n(42);
    e.exports = function(e) {
        var t = r(e);
        if (t < 0) throw RangeError("The argument can't be less than 0");
        return t
    }
}, function(e, t, n) {
    var r = n(22),
        o = n(13),
        i = n(77),
        a = n(104),
        c = n(58),
        s = n(2).aTypedArrayConstructor;
    e.exports = function(e) {
        var t, n, u, f, l, d, p = r(e),
            h = arguments.length,
            v = h > 1 ? arguments[1] : void 0,
            y = void 0 !== v,
            g = i(p);
        if (null != g && !a(g))
            for (d = (l = g.call(p)).next, p = []; !(f = d.call(l)).done;) p.push(f.value);
        for (y && h > 2 && (v = c(v, arguments[2], 2)), n = o(p.length), u = new(s(this))(n), t = 0; n > t; t++) u[t] = y ? v(p[t], t) : p[t];
        return u
    }
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(212),
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("copyWithin", (function(e, t) {
        return o.call(i(this), e, t, arguments.length > 2 ? arguments[2] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(22),
        o = n(57),
        i = n(13),
        a = Math.min;
    e.exports = [].copyWithin || function(e, t) {
        var n = r(this),
            c = i(n.length),
            s = o(e, c),
            u = o(t, c),
            f = arguments.length > 2 ? arguments[2] : void 0,
            l = a((void 0 === f ? c : o(f, c)) - u, c - s),
            d = 1;
        for (u < s && s < u + l && (d = -1, u += l - 1, s += l - 1); l-- > 0;) u in n ? n[s] = n[u] : delete n[s], s += d, u += d;
        return n
    }
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).every,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("every", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(154),
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("fill", (function(e) {
        return o.apply(i(this), arguments)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).filter,
        i = n(80),
        a = r.aTypedArray,
        c = r.aTypedArrayConstructor;
    (0, r.exportTypedArrayMethod)("filter", (function(e) {
        for (var t = o(a(this), e, arguments.length > 1 ? arguments[1] : void 0), n = i(this, this.constructor), r = 0, s = t.length, u = new(c(n))(s); s > r;) u[r] = t[r++];
        return u
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).find,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("find", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).findIndex,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("findIndex", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).forEach,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("forEach", (function(e) {
        o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(69).includes,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("includes", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(69).indexOf,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("indexOf", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(3),
        o = n(2),
        i = n(5),
        a = n(4)("iterator"),
        c = r.Uint8Array,
        s = i.values,
        u = i.keys,
        f = i.entries,
        l = o.aTypedArray,
        d = o.exportTypedArrayMethod,
        p = c && c.prototype[a],
        h = !!p && ("values" == p.name || null == p.name),
        v = function() {
            return s.call(l(this))
        };
    d("entries", (function() {
        return f.call(l(this))
    })), d("keys", (function() {
        return u.call(l(this))
    })), d("values", v, !h), d(a, v, !h)
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = r.aTypedArray,
        i = r.exportTypedArrayMethod,
        a = [].join;
    i("join", (function(e) {
        return a.apply(o(this), arguments)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(224),
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("lastIndexOf", (function(e) {
        return o.apply(i(this), arguments)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(28),
        o = n(42),
        i = n(13),
        a = n(71),
        c = n(39),
        s = Math.min,
        u = [].lastIndexOf,
        f = !!u && 1 / [1].lastIndexOf(1, -0) < 0,
        l = a("lastIndexOf"),
        d = c("indexOf", {
            ACCESSORS: !0,
            1: 0
        }),
        p = f || !l || !d;
    e.exports = p ? function(e) {
        if (f) return u.apply(this, arguments) || 0;
        var t = r(this),
            n = i(t.length),
            a = n - 1;
        for (arguments.length > 1 && (a = s(a, o(arguments[1]))), a < 0 && (a = n + a); a >= 0; a--)
            if (a in t && t[a] === e) return a || 0;
        return -1
    } : u
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).map,
        i = n(80),
        a = r.aTypedArray,
        c = r.aTypedArrayConstructor;
    (0, r.exportTypedArrayMethod)("map", (function(e) {
        return o(a(this), e, arguments.length > 1 ? arguments[1] : void 0, (function(e, t) {
            return new(c(i(e, e.constructor)))(t)
        }))
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(107).left,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("reduce", (function(e) {
        return o(i(this), e, arguments.length, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(107).right,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("reduceRight", (function(e) {
        return o(i(this), e, arguments.length, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = r.aTypedArray,
        i = r.exportTypedArrayMethod,
        a = Math.floor;
    i("reverse", (function() {
        for (var e, t = o(this).length, n = a(t / 2), r = 0; r < n;) e = this[r], this[r++] = this[--t], this[t] = e;
        return this
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(13),
        i = n(155),
        a = n(22),
        c = n(1),
        s = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("set", (function(e) {
        s(this);
        var t = i(arguments.length > 1 ? arguments[1] : void 0, 1),
            n = this.length,
            r = a(e),
            c = o(r.length),
            u = 0;
        if (c + t > n) throw RangeError("Wrong length");
        for (; u < c;) this[t + u] = r[u++]
    }), c((function() {
        new Int8Array(1).set({})
    })))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(80),
        i = n(1),
        a = r.aTypedArray,
        c = r.aTypedArrayConstructor,
        s = r.exportTypedArrayMethod,
        u = [].slice;
    s("slice", (function(e, t) {
        for (var n = u.call(a(this), e, t), r = o(this, this.constructor), i = 0, s = n.length, f = new(c(r))(s); s > i;) f[i] = n[i++];
        return f
    }), i((function() {
        new Int8Array(1).slice()
    })))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(21).some,
        i = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("some", (function(e) {
        return o(i(this), e, arguments.length > 1 ? arguments[1] : void 0)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = r.aTypedArray,
        i = r.exportTypedArrayMethod,
        a = [].sort;
    i("sort", (function(e) {
        return a.call(o(this), e)
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(2),
        o = n(13),
        i = n(57),
        a = n(80),
        c = r.aTypedArray;
    (0, r.exportTypedArrayMethod)("subarray", (function(e, t) {
        var n = c(this),
            r = n.length,
            s = i(e, r);
        return new(a(n, n.constructor))(n.buffer, n.byteOffset + s * n.BYTES_PER_ELEMENT, o((void 0 === t ? r : i(t, r)) - s))
    }))
}, function(e, t, n) {
    "use strict";
    var r = n(3),
        o = n(2),
        i = n(1),
        a = r.Int8Array,
        c = o.aTypedArray,
        s = o.exportTypedArrayMethod,
        u = [].toLocaleString,
        f = [].slice,
        l = !!a && i((function() {
            u.call(new a(1))
        }));
    s("toLocaleString", (function() {
        return u.apply(l ? f.call(c(this)) : c(this), arguments)
    }), i((function() {
        return [1, 2].toLocaleString() != new a([1, 2]).toLocaleString()
    })) || !i((function() {
        a.prototype.toLocaleString.call([1, 2])
    })))
}, function(e, t, n) {
    "use strict";
    var r = n(2).exportTypedArrayMethod,
        o = n(1),
        i = n(3).Uint8Array,
        a = i && i.prototype || {},
        c = [].toString,
        s = [].join;
    o((function() {
        c.call({})
    })) && (c = function() {
        return s.call(this)
    });
    var u = a.toString != c;
    r("toString", c, u)
}, function(e, t, n) {
    "use strict";
    var r, o = n(0),
        i = n(41).f,
        a = n(13),
        c = n(149),
        s = n(54),
        u = n(150),
        f = n(55),
        l = "".endsWith,
        d = Math.min,
        p = u("endsWith");
    o({
        target: "String",
        proto: !0,
        forced: !!(f || p || (r = i(String.prototype, "endsWith"), !r || r.writable)) && !p
    }, {
        endsWith: function(e) {
            var t = String(s(this));
            c(e);
            var n = arguments.length > 1 ? arguments[1] : void 0,
                r = a(t.length),
                o = void 0 === n ? r : d(a(n), r),
                i = String(e);
            return l ? l.call(t, i, o) : t.slice(o - i.length, o) === i
        }
    })
}, function(e, t, n) {
    "use strict";
    n(8);
    var r, o = n(0),
        i = n(9),
        a = n(156),
        c = n(3),
        s = n(99),
        u = n(29),
        f = n(50),
        l = n(11),
        d = n(238),
        p = n(140),
        h = n(101).codeAt,
        v = n(239),
        y = n(48),
        g = n(240),
        m = n(35),
        b = c.URL,
        w = g.URLSearchParams,
        _ = g.getState,
        O = m.set,
        E = m.getterFor("URL"),
        S = Math.floor,
        x = Math.pow,
        C = /[A-Za-z]/,
        j = /[\d+\-.A-Za-z]/,
        A = /\d/,
        k = /^(0x|0X)/,
        T = /^[0-7]+$/,
        P = /^\d+$/,
        L = /^[\dA-Fa-f]+$/,
        I = /[\u0000\u0009\u000A\u000D #%/:?@[\\]]/,
        R = /[\u0000\u0009\u000A\u000D #/:?@[\\]]/,
        M = /^[\u0000-\u001F ]+|[\u0000-\u001F ]+$/g,
        D = /[\u0009\u000A\u000D]/g,
        N = function(e, t) {
            var n, r, o;
            if ("[" == t.charAt(0)) {
                if ("]" != t.charAt(t.length - 1)) return "Invalid host";
                if (!(n = U(t.slice(1, -1)))) return "Invalid host";
                e.host = n
            } else if (X(e)) {
                if (t = v(t), I.test(t)) return "Invalid host";
                if (null === (n = F(t))) return "Invalid host";
                e.host = n
            } else {
                if (R.test(t)) return "Invalid host";
                for (n = "", r = p(t), o = 0; o < r.length; o++) n += q(r[o], B);
                e.host = n
            }
        },
        F = function(e) {
            var t, n, r, o, i, a, c, s = e.split(".");
            if (s.length && "" == s[s.length - 1] && s.pop(), (t = s.length) > 4) return e;
            for (n = [], r = 0; r < t; r++) {
                if ("" == (o = s[r])) return e;
                if (i = 10, o.length > 1 && "0" == o.charAt(0) && (i = k.test(o) ? 16 : 8, o = o.slice(8 == i ? 1 : 2)), "" === o) a = 0;
                else {
                    if (!(10 == i ? P : 8 == i ? T : L).test(o)) return e;
                    a = parseInt(o, i)
                }
                n.push(a)
            }
            for (r = 0; r < t; r++)
                if (a = n[r], r == t - 1) {
                    if (a >= x(256, 5 - t)) return null
                } else if (a > 255) return null;
            for (c = n.pop(), r = 0; r < n.length; r++) c += n[r] * x(256, 3 - r);
            return c
        },
        U = function(e) {
            var t, n, r, o, i, a, c, s = [0, 0, 0, 0, 0, 0, 0, 0],
                u = 0,
                f = null,
                l = 0,
                d = function() {
                    return e.charAt(l)
                };
            if (":" == d()) {
                if (":" != e.charAt(1)) return;
                l += 2, f = ++u
            }
            for (; d();) {
                if (8 == u) return;
                if (":" != d()) {
                    for (t = n = 0; n < 4 && L.test(d());) t = 16 * t + parseInt(d(), 16), l++, n++;
                    if ("." == d()) {
                        if (0 == n) return;
                        if (l -= n, u > 6) return;
                        for (r = 0; d();) {
                            if (o = null, r > 0) {
                                if (!("." == d() && r < 4)) return;
                                l++
                            }
                            if (!A.test(d())) return;
                            for (; A.test(d());) {
                                if (i = parseInt(d(), 10), null === o) o = i;
                                else {
                                    if (0 == o) return;
                                    o = 10 * o + i
                                }
                                if (o > 255) return;
                                l++
                            }
                            s[u] = 256 * s[u] + o, 2 != ++r && 4 != r || u++
                        }
                        if (4 != r) return;
                        break
                    }
                    if (":" == d()) {
                        if (l++, !d()) return
                    } else if (d()) return;
                    s[u++] = t
                } else {
                    if (null !== f) return;
                    l++, f = ++u
                }
            }
            if (null !== f)
                for (a = u - f, u = 7; 0 != u && a > 0;) c = s[u], s[u--] = s[f + a - 1], s[f + --a] = c;
            else if (8 != u) return;
            return s
        },
        z = function(e) {
            var t, n, r, o;
            if ("number" == typeof e) {
                for (t = [], n = 0; n < 4; n++) t.unshift(e % 256), e = S(e / 256);
                return t.join(".")
            }
            if ("object" == typeof e) {
                for (t = "", r = function(e) {
                        for (var t = null, n = 1, r = null, o = 0, i = 0; i < 8; i++) 0 !== e[i] ? (o > n && (t = r, n = o), r = null, o = 0) : (null === r && (r = i), ++o);
                        return o > n && (t = r, n = o), t
                    }(e), n = 0; n < 8; n++) o && 0 === e[n] || (o && (o = !1), r === n ? (t += n ? ":" : "::", o = !0) : (t += e[n].toString(16), n < 7 && (t += ":")));
                return "[" + t + "]"
            }
            return e
        },
        B = {},
        V = d({}, B, {
            " ": 1,
            '"': 1,
            "<": 1,
            ">": 1,
            "`": 1
        }),
        W = d({}, V, {
            "#": 1,
            "?": 1,
            "{": 1,
            "}": 1
        }),
        K = d({}, W, {
            "/": 1,
            ":": 1,
            ";": 1,
            "=": 1,
            "@": 1,
            "[": 1,
            "\\": 1,
            "]": 1,
            "^": 1,
            "|": 1
        }),
        q = function(e, t) {
            var n = h(e, 0);
            return n > 32 && n < 127 && !l(t, e) ? e : encodeURIComponent(e)
        },
        Y = {
            ftp: 21,
            file: null,
            http: 80,
            https: 443,
            ws: 80,
            wss: 443
        },
        X = function(e) {
            return l(Y, e.scheme)
        },
        H = function(e) {
            return "" != e.username || "" != e.password
        },
        $ = function(e) {
            return !e.host || e.cannotBeABaseURL || "file" == e.scheme
        },
        G = function(e, t) {
            var n;
            return 2 == e.length && C.test(e.charAt(0)) && (":" == (n = e.charAt(1)) || !t && "|" == n)
        },
        J = function(e) {
            var t;
            return e.length > 1 && G(e.slice(0, 2)) && (2 == e.length || "/" === (t = e.charAt(2)) || "\\" === t || "?" === t || "#" === t)
        },
        Z = function(e) {
            var t = e.path,
                n = t.length;
            !n || "file" == e.scheme && 1 == n && G(t[0], !0) || t.pop()
        },
        Q = function(e) {
            return "." === e || "%2e" === e.toLowerCase()
        },
        ee = {},
        te = {},
        ne = {},
        re = {},
        oe = {},
        ie = {},
        ae = {},
        ce = {},
        se = {},
        ue = {},
        fe = {},
        le = {},
        de = {},
        pe = {},
        he = {},
        ve = {},
        ye = {},
        ge = {},
        me = {},
        be = {},
        we = {},
        _e = function(e, t, n, o) {
            var i, a, c, s, u, f = n || ee,
                d = 0,
                h = "",
                v = !1,
                y = !1,
                g = !1;
            for (n || (e.scheme = "", e.username = "", e.password = "", e.host = null, e.port = null, e.path = [], e.query = null, e.fragment = null, e.cannotBeABaseURL = !1, t = t.replace(M, "")), t = t.replace(D, ""), i = p(t); d <= i.length;) {
                switch (a = i[d], f) {
                    case ee:
                        if (!a || !C.test(a)) {
                            if (n) return "Invalid scheme";
                            f = ne;
                            continue
                        }
                        h += a.toLowerCase(), f = te;
                        break;
                    case te:
                        if (a && (j.test(a) || "+" == a || "-" == a || "." == a)) h += a.toLowerCase();
                        else {
                            if (":" != a) {
                                if (n) return "Invalid scheme";
                                h = "", f = ne, d = 0;
                                continue
                            }
                            if (n && (X(e) != l(Y, h) || "file" == h && (H(e) || null !== e.port) || "file" == e.scheme && !e.host)) return;
                            if (e.scheme = h, n) return void(X(e) && Y[e.scheme] == e.port && (e.port = null));
                            h = "", "file" == e.scheme ? f = pe : X(e) && o && o.scheme == e.scheme ? f = re : X(e) ? f = ce : "/" == i[d + 1] ? (f = oe, d++) : (e.cannotBeABaseURL = !0, e.path.push(""), f = me)
                        }
                        break;
                    case ne:
                        if (!o || o.cannotBeABaseURL && "#" != a) return "Invalid scheme";
                        if (o.cannotBeABaseURL && "#" == a) {
                            e.scheme = o.scheme, e.path = o.path.slice(), e.query = o.query, e.fragment = "", e.cannotBeABaseURL = !0, f = we;
                            break
                        }
                        f = "file" == o.scheme ? pe : ie;
                        continue;
                    case re:
                        if ("/" != a || "/" != i[d + 1]) {
                            f = ie;
                            continue
                        }
                        f = se, d++;
                        break;
                    case oe:
                        if ("/" == a) {
                            f = ue;
                            break
                        }
                        f = ge;
                        continue;
                    case ie:
                        if (e.scheme = o.scheme, a == r) e.username = o.username, e.password = o.password, e.host = o.host, e.port = o.port, e.path = o.path.slice(), e.query = o.query;
                        else if ("/" == a || "\\" == a && X(e)) f = ae;
                        else if ("?" == a) e.username = o.username, e.password = o.password, e.host = o.host, e.port = o.port, e.path = o.path.slice(), e.query = "", f = be;
                        else {
                            if ("#" != a) {
                                e.username = o.username, e.password = o.password, e.host = o.host, e.port = o.port, e.path = o.path.slice(), e.path.pop(), f = ge;
                                continue
                            }
                            e.username = o.username, e.password = o.password, e.host = o.host, e.port = o.port, e.path = o.path.slice(), e.query = o.query, e.fragment = "", f = we
                        }
                        break;
                    case ae:
                        if (!X(e) || "/" != a && "\\" != a) {
                            if ("/" != a) {
                                e.username = o.username, e.password = o.password, e.host = o.host, e.port = o.port, f = ge;
                                continue
                            }
                            f = ue
                        } else f = se;
                        break;
                    case ce:
                        if (f = se, "/" != a || "/" != h.charAt(d + 1)) continue;
                        d++;
                        break;
                    case se:
                        if ("/" != a && "\\" != a) {
                            f = ue;
                            continue
                        }
                        break;
                    case ue:
                        if ("@" == a) {
                            v && (h = "%40" + h), v = !0, c = p(h);
                            for (var m = 0; m < c.length; m++) {
                                var b = c[m];
                                if (":" != b || g) {
                                    var w = q(b, K);
                                    g ? e.password += w : e.username += w
                                } else g = !0
                            }
                            h = ""
                        } else if (a == r || "/" == a || "?" == a || "#" == a || "\\" == a && X(e)) {
                            if (v && "" == h) return "Invalid authority";
                            d -= p(h).length + 1, h = "", f = fe
                        } else h += a;
                        break;
                    case fe:
                    case le:
                        if (n && "file" == e.scheme) {
                            f = ve;
                            continue
                        }
                        if (":" != a || y) {
                            if (a == r || "/" == a || "?" == a || "#" == a || "\\" == a && X(e)) {
                                if (X(e) && "" == h) return "Invalid host";
                                if (n && "" == h && (H(e) || null !== e.port)) return;
                                if (s = N(e, h)) return s;
                                if (h = "", f = ye, n) return;
                                continue
                            }
                            "[" == a ? y = !0 : "]" == a && (y = !1), h += a
                        } else {
                            if ("" == h) return "Invalid host";
                            if (s = N(e, h)) return s;
                            if (h = "", f = de, n == le) return
                        }
                        break;
                    case de:
                        if (!A.test(a)) {
                            if (a == r || "/" == a || "?" == a || "#" == a || "\\" == a && X(e) || n) {
                                if ("" != h) {
                                    var _ = parseInt(h, 10);
                                    if (_ > 65535) return "Invalid port";
                                    e.port = X(e) && _ === Y[e.scheme] ? null : _, h = ""
                                }
                                if (n) return;
                                f = ye;
                                continue
                            }
                            return "Invalid port"
                        }
                        h += a;
                        break;
                    case pe:
                        if (e.scheme = "file", "/" == a || "\\" == a) f = he;
                        else {
                            if (!o || "file" != o.scheme) {
                                f = ge;
                                continue
                            }
                            if (a == r) e.host = o.host, e.path = o.path.slice(), e.query = o.query;
                            else if ("?" == a) e.host = o.host, e.path = o.path.slice(), e.query = "", f = be;
                            else {
                                if ("#" != a) {
                                    J(i.slice(d).join("")) || (e.host = o.host, e.path = o.path.slice(), Z(e)), f = ge;
                                    continue
                                }
                                e.host = o.host, e.path = o.path.slice(), e.query = o.query, e.fragment = "", f = we
                            }
                        }
                        break;
                    case he:
                        if ("/" == a || "\\" == a) {
                            f = ve;
                            break
                        }
                        o && "file" == o.scheme && !J(i.slice(d).join("")) && (G(o.path[0], !0) ? e.path.push(o.path[0]) : e.host = o.host), f = ge;
                        continue;
                    case ve:
                        if (a == r || "/" == a || "\\" == a || "?" == a || "#" == a) {
                            if (!n && G(h)) f = ge;
                            else if ("" == h) {
                                if (e.host = "", n) return;
                                f = ye
                            } else {
                                if (s = N(e, h)) return s;
                                if ("localhost" == e.host && (e.host = ""), n) return;
                                h = "", f = ye
                            }
                            continue
                        }
                        h += a;
                        break;
                    case ye:
                        if (X(e)) {
                            if (f = ge, "/" != a && "\\" != a) continue
                        } else if (n || "?" != a)
                            if (n || "#" != a) {
                                if (a != r && (f = ge, "/" != a)) continue
                            } else e.fragment = "", f = we;
                        else e.query = "", f = be;
                        break;
                    case ge:
                        if (a == r || "/" == a || "\\" == a && X(e) || !n && ("?" == a || "#" == a)) {
                            if (".." === (u = (u = h).toLowerCase()) || "%2e." === u || ".%2e" === u || "%2e%2e" === u ? (Z(e), "/" == a || "\\" == a && X(e) || e.path.push("")) : Q(h) ? "/" == a || "\\" == a && X(e) || e.path.push("") : ("file" == e.scheme && !e.path.length && G(h) && (e.host && (e.host = ""), h = h.charAt(0) + ":"), e.path.push(h)), h = "", "file" == e.scheme && (a == r || "?" == a || "#" == a))
                                for (; e.path.length > 1 && "" === e.path[0];) e.path.shift();
                            "?" == a ? (e.query = "", f = be) : "#" == a && (e.fragment = "", f = we)
                        } else h += q(a, W);
                        break;
                    case me:
                        "?" == a ? (e.query = "", f = be) : "#" == a ? (e.fragment = "", f = we) : a != r && (e.path[0] += q(a, B));
                        break;
                    case be:
                        n || "#" != a ? a != r && ("'" == a && X(e) ? e.query += "%27" : e.query += "#" == a ? "%23" : q(a, B)) : (e.fragment = "", f = we);
                        break;
                    case we:
                        a != r && (e.fragment += q(a, V))
                }
                d++
            }
        },
        Oe = function(e) {
            var t, n, r = f(this, Oe, "URL"),
                o = arguments.length > 1 ? arguments[1] : void 0,
                a = String(e),
                c = O(r, {
                    type: "URL"
                });
            if (void 0 !== o)
                if (o instanceof Oe) t = E(o);
                else if (n = _e(t = {}, String(o))) throw TypeError(n);
            if (n = _e(c, a, null, t)) throw TypeError(n);
            var s = c.searchParams = new w,
                u = _(s);
            u.updateSearchParams(c.query), u.updateURL = function() {
                c.query = String(s) || null
            }, i || (r.href = Se.call(r), r.origin = xe.call(r), r.protocol = Ce.call(r), r.username = je.call(r), r.password = Ae.call(r), r.host = ke.call(r), r.hostname = Te.call(r), r.port = Pe.call(r), r.pathname = Le.call(r), r.search = Ie.call(r), r.searchParams = Re.call(r), r.hash = Me.call(r))
        },
        Ee = Oe.prototype,
        Se = function() {
            var e = E(this),
                t = e.scheme,
                n = e.username,
                r = e.password,
                o = e.host,
                i = e.port,
                a = e.path,
                c = e.query,
                s = e.fragment,
                u = t + ":";
            return null !== o ? (u += "//", H(e) && (u += n + (r ? ":" + r : "") + "@"), u += z(o), null !== i && (u += ":" + i)) : "file" == t && (u += "//"), u += e.cannotBeABaseURL ? a[0] : a.length ? "/" + a.join("/") : "", null !== c && (u += "?" + c), null !== s && (u += "#" + s), u
        },
        xe = function() {
            var e = E(this),
                t = e.scheme,
                n = e.port;
            if ("blob" == t) try {
                return new URL(t.path[0]).origin
            } catch (e) {
                return "null"
            }
            return "file" != t && X(e) ? t + "://" + z(e.host) + (null !== n ? ":" + n : "") : "null"
        },
        Ce = function() {
            return E(this).scheme + ":"
        },
        je = function() {
            return E(this).username
        },
        Ae = function() {
            return E(this).password
        },
        ke = function() {
            var e = E(this),
                t = e.host,
                n = e.port;
            return null === t ? "" : null === n ? z(t) : z(t) + ":" + n
        },
        Te = function() {
            var e = E(this).host;
            return null === e ? "" : z(e)
        },
        Pe = function() {
            var e = E(this).port;
            return null === e ? "" : String(e)
        },
        Le = function() {
            var e = E(this),
                t = e.path;
            return e.cannotBeABaseURL ? t[0] : t.length ? "/" + t.join("/") : ""
        },
        Ie = function() {
            var e = E(this).query;
            return e ? "?" + e : ""
        },
        Re = function() {
            return E(this).searchParams
        },
        Me = function() {
            var e = E(this).fragment;
            return e ? "#" + e : ""
        },
        De = function(e, t) {
            return {
                get: e,
                set: t,
                configurable: !0,
                enumerable: !0
            }
        };
    if (i && s(Ee, {
            href: De(Se, (function(e) {
                var t = E(this),
                    n = String(e),
                    r = _e(t, n);
                if (r) throw TypeError(r);
                _(t.searchParams).updateSearchParams(t.query)
            })),
            origin: De(xe),
            protocol: De(Ce, (function(e) {
                var t = E(this);
                _e(t, String(e) + ":", ee)
            })),
            username: De(je, (function(e) {
                var t = E(this),
                    n = p(String(e));
                if (!$(t)) {
                    t.username = "";
                    for (var r = 0; r < n.length; r++) t.username += q(n[r], K)
                }
            })),
            password: De(Ae, (function(e) {
                var t = E(this),
                    n = p(String(e));
                if (!$(t)) {
                    t.password = "";
                    for (var r = 0; r < n.length; r++) t.password += q(n[r], K)
                }
            })),
            host: De(ke, (function(e) {
                var t = E(this);
                t.cannotBeABaseURL || _e(t, String(e), fe)
            })),
            hostname: De(Te, (function(e) {
                var t = E(this);
                t.cannotBeABaseURL || _e(t, String(e), le)
            })),
            port: De(Pe, (function(e) {
                var t = E(this);
                $(t) || ("" == (e = String(e)) ? t.port = null : _e(t, e, de))
            })),
            pathname: De(Le, (function(e) {
                var t = E(this);
                t.cannotBeABaseURL || (t.path = [], _e(t, e + "", ye))
            })),
            search: De(Ie, (function(e) {
                var t = E(this);
                "" == (e = String(e)) ? t.query = null: ("?" == e.charAt(0) && (e = e.slice(1)), t.query = "", _e(t, e, be)), _(t.searchParams).updateSearchParams(t.query)
            })),
            searchParams: De(Re),
            hash: De(Me, (function(e) {
                var t = E(this);
                "" != (e = String(e)) ? ("#" == e.charAt(0) && (e = e.slice(1)), t.fragment = "", _e(t, e, we)) : t.fragment = null
            }))
        }), u(Ee, "toJSON", (function() {
            return Se.call(this)
        }), {
            enumerable: !0
        }), u(Ee, "toString", (function() {
            return Se.call(this)
        }), {
            enumerable: !0
        }), b) {
        var Ne = b.createObjectURL,
            Fe = b.revokeObjectURL;
        Ne && u(Oe, "createObjectURL", (function(e) {
            return Ne.apply(b, arguments)
        })), Fe && u(Oe, "revokeObjectURL", (function(e) {
            return Fe.apply(b, arguments)
        }))
    }
    y(Oe, "URL"), o({
        global: !0,
        forced: !a,
        sham: !i
    }, {
        URL: Oe
    })
}, function(e, t, n) {
    "use strict";
    var r = n(9),
        o = n(1),
        i = n(73),
        a = n(92),
        c = n(82),
        s = n(22),
        u = n(63),
        f = Object.assign,
        l = Object.defineProperty;
    e.exports = !f || o((function() {
        if (r && 1 !== f({
                b: 1
            }, f(l({}, "a", {
                enumerable: !0,
                get: function() {
                    l(this, "b", {
                        value: 3,
                        enumerable: !1
                    })
                }
            }), {
                b: 2
            })).b) return !0;
        var e = {},
            t = {},
            n = Symbol();
        return e[n] = 7, "abcdefghijklmnopqrst".split("").forEach((function(e) {
            t[e] = e
        })), 7 != f({}, e)[n] || "abcdefghijklmnopqrst" != i(f({}, t)).join("")
    })) ? function(e, t) {
        for (var n = s(e), o = arguments.length, f = 1, l = a.f, d = c.f; o > f;)
            for (var p, h = u(arguments[f++]), v = l ? i(h).concat(l(h)) : i(h), y = v.length, g = 0; y > g;) p = v[g++], r && !d.call(h, p) || (n[p] = h[p]);
        return n
    } : f
}, function(e, t, n) {
    "use strict";
    var r = /[^\0-\u007E]/,
        o = /[.\u3002\uFF0E\uFF61]/g,
        i = "Overflow: input needs wider integers to process",
        a = Math.floor,
        c = String.fromCharCode,
        s = function(e) {
            return e + 22 + 75 * (e < 26)
        },
        u = function(e, t, n) {
            var r = 0;
            for (e = n ? a(e / 700) : e >> 1, e += a(e / t); e > 455; r += 36) e = a(e / 35);
            return a(r + 36 * e / (e + 38))
        },
        f = function(e) {
            var t, n, r = [],
                o = (e = function(e) {
                    for (var t = [], n = 0, r = e.length; n < r;) {
                        var o = e.charCodeAt(n++);
                        if (o >= 55296 && o <= 56319 && n < r) {
                            var i = e.charCodeAt(n++);
                            56320 == (64512 & i) ? t.push(((1023 & o) << 10) + (1023 & i) + 65536) : (t.push(o), n--)
                        } else t.push(o)
                    }
                    return t
                }(e)).length,
                f = 128,
                l = 0,
                d = 72;
            for (t = 0; t < e.length; t++)(n = e[t]) < 128 && r.push(c(n));
            var p = r.length,
                h = p;
            for (p && r.push("-"); h < o;) {
                var v = 2147483647;
                for (t = 0; t < e.length; t++)(n = e[t]) >= f && n < v && (v = n);
                var y = h + 1;
                if (v - f > a((2147483647 - l) / y)) throw RangeError(i);
                for (l += (v - f) * y, f = v, t = 0; t < e.length; t++) {
                    if ((n = e[t]) < f && ++l > 2147483647) throw RangeError(i);
                    if (n == f) {
                        for (var g = l, m = 36;; m += 36) {
                            var b = m <= d ? 1 : m >= d + 26 ? 26 : m - d;
                            if (g < b) break;
                            var w = g - b,
                                _ = 36 - b;
                            r.push(c(s(b + w % _))), g = a(w / _)
                        }
                        r.push(c(s(g))), d = u(l, y, h == p), l = 0, ++h
                    }
                }++l, ++f
            }
            return r.join("")
        };
    e.exports = function(e) {
        var t, n, i = [],
            a = e.toLowerCase().replace(o, ".").split(".");
        for (t = 0; t < a.length; t++) n = a[t], i.push(r.test(n) ? "xn--" + f(n) : n);
        return i.join(".")
    }
}, function(e, t, n) {
    "use strict";
    n(5);
    var r = n(0),
        o = n(56),
        i = n(156),
        a = n(29),
        c = n(76),
        s = n(48),
        u = n(126),
        f = n(35),
        l = n(50),
        d = n(11),
        p = n(58),
        h = n(72),
        v = n(17),
        y = n(10),
        g = n(59),
        m = n(47),
        b = n(241),
        w = n(77),
        _ = n(4),
        O = o("fetch"),
        E = o("Headers"),
        S = _("iterator"),
        x = f.set,
        C = f.getterFor("URLSearchParams"),
        j = f.getterFor("URLSearchParamsIterator"),
        A = /\+/g,
        k = Array(4),
        T = function(e) {
            return k[e - 1] || (k[e - 1] = RegExp("((?:%[\\da-f]{2}){" + e + "})", "gi"))
        },
        P = function(e) {
            try {
                return decodeURIComponent(e)
            } catch (t) {
                return e
            }
        },
        L = function(e) {
            var t = e.replace(A, " "),
                n = 4;
            try {
                return decodeURIComponent(t)
            } catch (e) {
                for (; n;) t = t.replace(T(n--), P);
                return t
            }
        },
        I = /[!'()~]|%20/g,
        R = {
            "!": "%21",
            "'": "%27",
            "(": "%28",
            ")": "%29",
            "~": "%7E",
            "%20": "+"
        },
        M = function(e) {
            return R[e]
        },
        D = function(e) {
            return encodeURIComponent(e).replace(I, M)
        },
        N = function(e, t) {
            if (t)
                for (var n, r, o = t.split("&"), i = 0; i < o.length;)(n = o[i++]).length && (r = n.split("="), e.push({
                    key: L(r.shift()),
                    value: L(r.join("="))
                }))
        },
        F = function(e) {
            this.entries.length = 0, N(this.entries, e)
        },
        U = function(e, t) {
            if (e < t) throw TypeError("Not enough arguments")
        },
        z = u((function(e, t) {
            x(this, {
                type: "URLSearchParamsIterator",
                iterator: b(C(e).entries),
                kind: t
            })
        }), "Iterator", (function() {
            var e = j(this),
                t = e.kind,
                n = e.iterator.next(),
                r = n.value;
            return n.done || (n.value = "keys" === t ? r.key : "values" === t ? r.value : [r.key, r.value]), n
        })),
        B = function() {
            l(this, B, "URLSearchParams");
            var e, t, n, r, o, i, a, c, s, u = arguments.length > 0 ? arguments[0] : void 0,
                f = this,
                p = [];
            if (x(f, {
                    type: "URLSearchParams",
                    entries: p,
                    updateURL: function() {},
                    updateSearchParams: F
                }), void 0 !== u)
                if (y(u))
                    if ("function" == typeof(e = w(u)))
                        for (n = (t = e.call(u)).next; !(r = n.call(t)).done;) {
                            if ((a = (i = (o = b(v(r.value))).next).call(o)).done || (c = i.call(o)).done || !i.call(o).done) throw TypeError("Expected sequence with length 2");
                            p.push({
                                key: a.value + "",
                                value: c.value + ""
                            })
                        } else
                            for (s in u) d(u, s) && p.push({
                                key: s,
                                value: u[s] + ""
                            });
                    else N(p, "string" == typeof u ? "?" === u.charAt(0) ? u.slice(1) : u : u + "")
        },
        V = B.prototype;
    c(V, {
        append: function(e, t) {
            U(arguments.length, 2);
            var n = C(this);
            n.entries.push({
                key: e + "",
                value: t + ""
            }), n.updateURL()
        },
        delete: function(e) {
            U(arguments.length, 1);
            for (var t = C(this), n = t.entries, r = e + "", o = 0; o < n.length;) n[o].key === r ? n.splice(o, 1) : o++;
            t.updateURL()
        },
        get: function(e) {
            U(arguments.length, 1);
            for (var t = C(this).entries, n = e + "", r = 0; r < t.length; r++)
                if (t[r].key === n) return t[r].value;
            return null
        },
        getAll: function(e) {
            U(arguments.length, 1);
            for (var t = C(this).entries, n = e + "", r = [], o = 0; o < t.length; o++) t[o].key === n && r.push(t[o].value);
            return r
        },
        has: function(e) {
            U(arguments.length, 1);
            for (var t = C(this).entries, n = e + "", r = 0; r < t.length;)
                if (t[r++].key === n) return !0;
            return !1
        },
        set: function(e, t) {
            U(arguments.length, 1);
            for (var n, r = C(this), o = r.entries, i = !1, a = e + "", c = t + "", s = 0; s < o.length; s++)(n = o[s]).key === a && (i ? o.splice(s--, 1) : (i = !0, n.value = c));
            i || o.push({
                key: a,
                value: c
            }), r.updateURL()
        },
        sort: function() {
            var e, t, n, r = C(this),
                o = r.entries,
                i = o.slice();
            for (o.length = 0, n = 0; n < i.length; n++) {
                for (e = i[n], t = 0; t < n; t++)
                    if (o[t].key > e.key) {
                        o.splice(t, 0, e);
                        break
                    } t === n && o.push(e)
            }
            r.updateURL()
        },
        forEach: function(e) {
            for (var t, n = C(this).entries, r = p(e, arguments.length > 1 ? arguments[1] : void 0, 3), o = 0; o < n.length;) r((t = n[o++]).value, t.key, this)
        },
        keys: function() {
            return new z(this, "keys")
        },
        values: function() {
            return new z(this, "values")
        },
        entries: function() {
            return new z(this, "entries")
        }
    }, {
        enumerable: !0
    }), a(V, S, V.entries), a(V, "toString", (function() {
        for (var e, t = C(this).entries, n = [], r = 0; r < t.length;) e = t[r++], n.push(D(e.key) + "=" + D(e.value));
        return n.join("&")
    }), {
        enumerable: !0
    }), s(B, "URLSearchParams"), r({
        global: !0,
        forced: !i
    }, {
        URLSearchParams: B
    }), i || "function" != typeof O || "function" != typeof E || r({
        global: !0,
        enumerable: !0,
        forced: !0
    }, {
        fetch: function(e) {
            var t, n, r, o = [e];
            return arguments.length > 1 && (t = arguments[1], y(t) && (n = t.body, "URLSearchParams" === h(n) && ((r = t.headers ? new E(t.headers) : new E).has("content-type") || r.set("content-type", "application/x-www-form-urlencoded;charset=UTF-8"), t = g(t, {
                body: m(0, String(n)),
                headers: m(0, r)
            }))), o.push(t)), O.apply(this, o)
        }
    }), e.exports = {
        URLSearchParams: B,
        getState: C
    }
}, function(e, t, n) {
    var r = n(17),
        o = n(77);
    e.exports = function(e) {
        var t = o(e);
        if ("function" != typeof t) throw TypeError(String(e) + " is not iterable");
        return r(t.call(e))
    }
}, function(e, t, n) {
    "use strict";
    var r = "undefined" != typeof crypto && crypto.getRandomValues && crypto.getRandomValues.bind(crypto) || "undefined" != typeof msCrypto && "function" == typeof msCrypto.getRandomValues && msCrypto.getRandomValues.bind(msCrypto),
        o = new Uint8Array(16);

    function i() {
        if (!r) throw new Error("crypto.getRandomValues() not supported. See https://github.com/uuidjs/uuid#getrandomvalues-not-supported");
        return r(o)
    }
    for (var a = [], c = 0; c < 256; ++c) a[c] = (c + 256).toString(16).substr(1);
    var s = function(e, t) {
        var n = t || 0,
            r = a;
        return [r[e[n++]], r[e[n++]], r[e[n++]], r[e[n++]], "-", r[e[n++]], r[e[n++]], "-", r[e[n++]], r[e[n++]], "-", r[e[n++]], r[e[n++]], "-", r[e[n++]], r[e[n++]], r[e[n++]], r[e[n++]], r[e[n++]], r[e[n++]]].join("")
    };
    t.a = function(e, t, n) {
        var r = t && n || 0;
        "string" == typeof e && (t = "binary" === e ? new Array(16) : null, e = null);
        var o = (e = e || {}).random || (e.rng || i)();
        if (o[6] = 15 & o[6] | 64, o[8] = 63 & o[8] | 128, t)
            for (var a = 0; a < 16; ++a) t[r + a] = o[a];
        return t || s(o)
    }
}]).default;
//# sourceMappingURL=CobrowseIO.js.map 