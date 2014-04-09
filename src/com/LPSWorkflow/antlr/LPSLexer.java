// Generated from LPS.g4 by ANTLR 4.x

package com.LPSWorkflow.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import java.util.Map;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPSLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, END=18, ID=19, NEG=20, WS=21, COMMENT=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'initiates'", "'DomainTheory'", "'->'", "'terminates'", "'&'", "')'", 
		"','", "'ReactiveRules'", "'('", "':'", "'false'", "'Fluents'", "'||'", 
		"'{'", "'Goals'", "'}'", "'<-'", "'.'", "ID", "NEG", "WS", "COMMENT"
	};
	public static final Map<String, Integer> tokenNameToType = Utils.toMap(tokenNames);
	public static final String[] ruleNames = {
		"T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", 
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"END", "ID", "NEG", "WS", "COMMENT"
	};
	public static final Map<String, Integer> ruleNameToIndex = Utils.toMap(ruleNames);

	@Override
	public Map<String, Integer> getTokenTypeMap() { return tokenNameToType; }
	@Override
	public Map<String, Integer> getRuleIndexMap() { return ruleNameToIndex; }


	public LPSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LPS.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 20: WS_action((RuleContext)_localctx, actionIndex); break;
		case 21: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\2\30\u00b7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\22\3\23\3\23\3\24\6\24\u008e\n\24\r\24\16\24\u008f\3\25"+
		"\3\25\3\25\3\26\6\26\u0096\n\26\r\26\16\26\u0097\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\7\27\u00a0\n\27\f\27\16\27\u00a3\13\27\3\27\5\27\u00a6\n\27\3"+
		"\27\3\27\3\27\3\27\3\27\7\27\u00ad\n\27\f\27\16\27\u00b0\13\27\3\27\3"+
		"\27\5\27\u00b4\n\27\3\27\3\27\3\u00ae\2\30\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1"+
		"\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\2-\30\3\3\2\5\6\2\62;C\\a"+
		"ac|\6\2\13\f\17\17\"\"^^\4\2\f\f\17\17\u00bc\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\59\3\2\2\2\7F\3\2\2\2\t"+
		"I\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2\2\21Z\3\2\2\2\23h\3\2\2\2\25"+
		"j\3\2\2\2\27l\3\2\2\2\31r\3\2\2\2\33z\3\2\2\2\35}\3\2\2\2\37\177\3\2\2"+
		"\2!\u0085\3\2\2\2#\u0087\3\2\2\2%\u008a\3\2\2\2\'\u008d\3\2\2\2)\u0091"+
		"\3\2\2\2+\u0095\3\2\2\2-\u00b3\3\2\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7"+
		"k\2\2\62\63\7v\2\2\63\64\7k\2\2\64\65\7c\2\2\65\66\7v\2\2\66\67\7g\2\2"+
		"\678\7u\2\28\4\3\2\2\29:\7F\2\2:;\7q\2\2;<\7o\2\2<=\7c\2\2=>\7k\2\2>?"+
		"\7p\2\2?@\7V\2\2@A\7j\2\2AB\7g\2\2BC\7q\2\2CD\7t\2\2DE\7{\2\2E\6\3\2\2"+
		"\2FG\7/\2\2GH\7@\2\2H\b\3\2\2\2IJ\7v\2\2JK\7g\2\2KL\7t\2\2LM\7o\2\2MN"+
		"\7k\2\2NO\7p\2\2OP\7c\2\2PQ\7v\2\2QR\7g\2\2RS\7u\2\2S\n\3\2\2\2TU\7(\2"+
		"\2U\f\3\2\2\2VW\7+\2\2W\16\3\2\2\2XY\7.\2\2Y\20\3\2\2\2Z[\7T\2\2[\\\7"+
		"g\2\2\\]\7c\2\2]^\7e\2\2^_\7v\2\2_`\7k\2\2`a\7x\2\2ab\7g\2\2bc\7T\2\2"+
		"cd\7w\2\2de\7n\2\2ef\7g\2\2fg\7u\2\2g\22\3\2\2\2hi\7*\2\2i\24\3\2\2\2"+
		"jk\7<\2\2k\26\3\2\2\2lm\7h\2\2mn\7c\2\2no\7n\2\2op\7u\2\2pq\7g\2\2q\30"+
		"\3\2\2\2rs\7H\2\2st\7n\2\2tu\7w\2\2uv\7g\2\2vw\7p\2\2wx\7v\2\2xy\7u\2"+
		"\2y\32\3\2\2\2z{\7~\2\2{|\7~\2\2|\34\3\2\2\2}~\7}\2\2~\36\3\2\2\2\177"+
		"\u0080\7I\2\2\u0080\u0081\7q\2\2\u0081\u0082\7c\2\2\u0082\u0083\7n\2\2"+
		"\u0083\u0084\7u\2\2\u0084 \3\2\2\2\u0085\u0086\7\177\2\2\u0086\"\3\2\2"+
		"\2\u0087\u0088\7>\2\2\u0088\u0089\7/\2\2\u0089$\3\2\2\2\u008a\u008b\7"+
		"\60\2\2\u008b&\3\2\2\2\u008c\u008e\t\2\2\2\u008d\u008c\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090(\3\2\2\2\u0091"+
		"\u0092\7#\2\2\u0092\u0093\5\'\24\2\u0093*\3\2\2\2\u0094\u0096\t\3\2\2"+
		"\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\b\26\2\2\u009a,\3\2\2\2\u009b"+
		"\u009c\7\61\2\2\u009c\u009d\7\61\2\2\u009d\u00a1\3\2\2\2\u009e\u00a0\n"+
		"\4\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\7\17"+
		"\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00b4\7\f\2\2\u00a8\u00a9\7\61\2\2\u00a9\u00aa\7,\2\2\u00aa\u00ae\3\2"+
		"\2\2\u00ab\u00ad\13\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2"+
		"\2\2\u00b1\u00b2\7,\2\2\u00b2\u00b4\7\61\2\2\u00b3\u009b\3\2\2\2\u00b3"+
		"\u00a8\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\27\3\2\u00b6.\3\2\2\2"+
		"\t\2\u008f\u0097\u00a1\u00a5\u00ae\u00b3";
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