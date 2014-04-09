// Generated from LPS.g4 by ANTLR 4.x

package com.LPSWorkflow.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPSParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__13=1, T__12=2, T__11=3, T__10=4, T__9=5, T__8=6, T__7=7, T__6=8, T__5=9, 
		T__4=10, T__3=11, T__2=12, T__1=13, T__0=14, END=15, ID=16, NEG=17, WS=18;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "')'", "','", "'ReactiveRules'", "':'", "'('", "'Fluents'", 
		"'aaaaa'", "'DSet'", "'||'", "'{'", "'Goals'", "'}'", "'<-'", "'.'", "ID", 
		"NEG", "WS"
	};
	public static final Map<String, Integer> tokenNameToType = Utils.toMap(tokenNames);
	public static final int
		RULE_program = 0, RULE_reactiveRules = 1, RULE_r = 2, RULE_goals = 3, 
		RULE_g = 4, RULE_fluents = 5, RULE_f = 6, RULE_dset = 7, RULE_d = 8, RULE_formula = 9, 
		RULE_atom = 10;
	public static final String[] ruleNames = {
		"program", "reactiveRules", "r", "goals", "g", "fluents", "f", "dset", 
		"d", "formula", "atom"
	};
	public static final Map<String, Integer> ruleNameToIndex = Utils.toMap(ruleNames);

	@Override
	public String getGrammarFileName() { return "LPS.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }
	@Override
	public ATN getATNWithBypassAlts() { return _ATNWithBypassAlts; }
	@Override
	public void setATNWithBypassAlts(ATN atn) { _ATNWithBypassAlts = atn; }

	@Override
	public Map<String, Integer> getTokenTypeMap() { return tokenNameToType; }
	@Override
	public Map<String, Integer> getRuleIndexMap() { return ruleNameToIndex; }

	public LPSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<GoalsContext> goals() {
			return getRuleContexts(GoalsContext.class);
		}
		public ReactiveRulesContext reactiveRules(int i) {
			return getRuleContext(ReactiveRulesContext.class,i);
		}
		public List<ReactiveRulesContext> reactiveRules() {
			return getRuleContexts(ReactiveRulesContext.class);
		}
		public List<FluentsContext> fluents() {
			return getRuleContexts(FluentsContext.class);
		}
		public GoalsContext goals(int i) {
			return getRuleContext(GoalsContext.class,i);
		}
		public FluentsContext fluents(int i) {
			return getRuleContext(FluentsContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 7) | (1L << 12))) != 0)) {
				{
				setState(25);
				switch (_input.LA(1)) {
				case 4:
					{
					setState(22); reactiveRules();
					}
					break;
				case 12:
					{
					setState(23); goals();
					}
					break;
				case 7:
					{
					setState(24); fluents();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactiveRulesContext extends ParserRuleContext {
		public TerminalNode END(int i) {
			return getToken(LPSParser.END, i);
		}
		public List<RContext> r() {
			return getRuleContexts(RContext.class);
		}
		public RContext r(int i) {
			return getRuleContext(RContext.class,i);
		}
		public List<TerminalNode> END() { return getTokens(LPSParser.END); }
		public ReactiveRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactiveRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterReactiveRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitReactiveRules(this);
		}
	}

	public final ReactiveRulesContext reactiveRules() throws RecognitionException {
		ReactiveRulesContext _localctx = new ReactiveRulesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reactiveRules);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(4);
			setState(31); match(11);
			setState(40);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << ID) | (1L << NEG))) != 0)) {
				{
				setState(32); r();
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(33); match(END);
						setState(34); r();
						}
						} 
					}
					setState(39);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				}
			}

			setState(43);
			_la = _input.LA(1);
			if (_la==END) {
				{
				setState(42); match(END);
				}
			}

			setState(45); match(13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RContext extends ParserRuleContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); formula(0);
			setState(48); match(1);
			setState(49); formula(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoalsContext extends ParserRuleContext {
		public TerminalNode END(int i) {
			return getToken(LPSParser.END, i);
		}
		public GContext g(int i) {
			return getRuleContext(GContext.class,i);
		}
		public List<GContext> g() {
			return getRuleContexts(GContext.class);
		}
		public List<TerminalNode> END() { return getTokens(LPSParser.END); }
		public GoalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterGoals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitGoals(this);
		}
	}

	public final GoalsContext goals() throws RecognitionException {
		GoalsContext _localctx = new GoalsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_goals);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51); match(12);
			setState(52); match(11);
			setState(61);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(53); g();
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(54); match(END);
						setState(55); g();
						}
						} 
					}
					setState(60);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
			}

			setState(64);
			_la = _input.LA(1);
			if (_la==END) {
				{
				setState(63); match(END);
				}
			}

			setState(66); match(13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GContext extends ParserRuleContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public GContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitG(this);
		}
	}

	public final GContext g() throws RecognitionException {
		GContext _localctx = new GContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_g);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); atom();
			setState(69); match(14);
			setState(70); formula(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FluentsContext extends ParserRuleContext {
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FluentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fluents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterFluents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitFluents(this);
		}
	}

	public final FluentsContext fluents() throws RecognitionException {
		FluentsContext _localctx = new FluentsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fluents);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(7);
			setState(73); match(11);
			setState(82);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(74); f();
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(75); match(3);
						setState(76); f();
						}
						} 
					}
					setState(81);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				}
			}

			setState(85);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(84); match(3);
				}
			}

			setState(87); match(13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitF(this);
		}
	}

	public final FContext f() throws RecognitionException {
		FContext _localctx = new FContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DsetContext extends ParserRuleContext {
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public DsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterDset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitDset(this);
		}
	}

	public final DsetContext dset() throws RecognitionException {
		DsetContext _localctx = new DsetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(9);
			setState(92); match(11);
			setState(93); d();
			setState(94); match(13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DContext extends ParserRuleContext {
		public DContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitD(this);
		}
	}

	public final DContext d() throws RecognitionException {
		DContext _localctx = new DContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_d);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomicContext extends FormulaContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomicContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterAtomic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitAtomic(this);
		}
	}
	public static class PartialOrderContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public PartialOrderContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterPartialOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitPartialOrder(this);
		}
	}
	public static class BracketContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public BracketContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitBracket(this);
		}
	}
	public static class SequenceContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public SequenceContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitSequence(this);
		}
	}
	public static class ConcurrentContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public ConcurrentContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterConcurrent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitConcurrent(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			switch (_input.LA(1)) {
			case 6:
				{
				_localctx = new BracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(99); match(6);
				setState(100); formula(0);
				setState(101); match(2);
				}
				break;
			case ID:
			case NEG:
				{
				_localctx = new AtomicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(115);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new SequenceContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(106);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(107); match(3);
						setState(108); formula(6);
						}
						break;
					case 2:
						{
						_localctx = new ConcurrentContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(109);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(110); match(5);
						setState(111); formula(5);
						}
						break;
					case 3:
						{
						_localctx = new PartialOrderContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(112);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(113); match(10);
						setState(114); formula(4);
						}
						break;
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LPSParser.ID, 0); }
		public TerminalNode NEG() { return getToken(LPSParser.NEG, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NEG) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9: return formula_sempred((FormulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 5);
		case 1: return precpred(_ctx, 4);
		case 2: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\3\24}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\3\3\3\3\3\3\3\3\3\7\3"+
		"&\n\3\f\3\16\3)\13\3\5\3+\n\3\3\3\5\3.\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\5\5@\n\5\3\5\5\5C\n\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7P\n\7\f\7\16\7S\13\7\5\7U\n\7"+
		"\3\7\5\7X\n\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13k\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13v\n\13\f\13\16\13y\13\13\3\f\3\f\3\f\2\3\24\r\2\4\6\b\n\f\16"+
		"\20\22\24\26\2\3\3\2\22\23\u0081\2\35\3\2\2\2\4 \3\2\2\2\6\61\3\2\2\2"+
		"\b\65\3\2\2\2\nF\3\2\2\2\fJ\3\2\2\2\16[\3\2\2\2\20]\3\2\2\2\22b\3\2\2"+
		"\2\24j\3\2\2\2\26z\3\2\2\2\30\34\5\4\3\2\31\34\5\b\5\2\32\34\5\f\7\2\33"+
		"\30\3\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35"+
		"\36\3\2\2\2\36\3\3\2\2\2\37\35\3\2\2\2 !\7\6\2\2!*\7\r\2\2\"\'\5\6\4\2"+
		"#$\7\21\2\2$&\5\6\4\2%#\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(+\3\2"+
		"\2\2)\'\3\2\2\2*\"\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\7\21\2\2-,\3\2\2\2-."+
		"\3\2\2\2./\3\2\2\2/\60\7\17\2\2\60\5\3\2\2\2\61\62\5\24\13\2\62\63\7\3"+
		"\2\2\63\64\5\24\13\2\64\7\3\2\2\2\65\66\7\16\2\2\66?\7\r\2\2\67<\5\n\6"+
		"\289\7\21\2\29;\5\n\6\2:8\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=@\3\2"+
		"\2\2><\3\2\2\2?\67\3\2\2\2?@\3\2\2\2@B\3\2\2\2AC\7\21\2\2BA\3\2\2\2BC"+
		"\3\2\2\2CD\3\2\2\2DE\7\17\2\2E\t\3\2\2\2FG\5\26\f\2GH\7\20\2\2HI\5\24"+
		"\13\2I\13\3\2\2\2JK\7\t\2\2KT\7\r\2\2LQ\5\16\b\2MN\7\5\2\2NP\5\16\b\2"+
		"OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RU\3\2\2\2SQ\3\2\2\2TL\3\2\2\2"+
		"TU\3\2\2\2UW\3\2\2\2VX\7\5\2\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\17\2"+
		"\2Z\r\3\2\2\2[\\\5\26\f\2\\\17\3\2\2\2]^\7\13\2\2^_\7\r\2\2_`\5\22\n\2"+
		"`a\7\17\2\2a\21\3\2\2\2bc\7\n\2\2c\23\3\2\2\2de\b\13\1\2ef\7\b\2\2fg\5"+
		"\24\13\2gh\7\4\2\2hk\3\2\2\2ik\5\26\f\2jd\3\2\2\2ji\3\2\2\2kw\3\2\2\2"+
		"lm\f\7\2\2mn\7\5\2\2nv\5\24\13\bop\f\6\2\2pq\7\7\2\2qv\5\24\13\7rs\f\5"+
		"\2\2st\7\f\2\2tv\5\24\13\6ul\3\2\2\2uo\3\2\2\2ur\3\2\2\2vy\3\2\2\2wu\3"+
		"\2\2\2wx\3\2\2\2x\25\3\2\2\2yw\3\2\2\2z{\t\2\2\2{\27\3\2\2\2\20\33\35"+
		"\'*-<?BQTWjuw";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	public static ATN _ATNWithBypassAlts;
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}