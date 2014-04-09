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
		T__13=1, T__12=2, T__11=3, T__10=4, T__9=5, T__8=6, T__7=7, T__6=8, T__5=9, 
		T__4=10, T__3=11, T__2=12, T__1=13, T__0=14, END=15, ID=16, NEG=17, WS=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'->'", "')'", "','", "'ReactiveRules'", "':'", "'('", "'Fluents'", "'aaaaa'", 
		"'DSet'", "'||'", "'{'", "'Goals'", "'}'", "'<-'", "'.'", "ID", "NEG", 
		"WS"
	};
	public static final Map<String, Integer> tokenNameToType = Utils.toMap(tokenNames);
	public static final String[] ruleNames = {
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "END", "ID", "NEG", "WS"
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
		case 17: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\2\24t\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\6\21"+
		"g\n\21\r\21\16\21h\3\22\3\22\3\22\3\23\6\23o\n\23\r\23\16\23p\3\23\3\23"+
		"\2\2\24\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25"+
		"\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\2\3\2\4\6"+
		"\2\62;C\\aac|\6\2\13\f\17\17\"\"^^u\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5*\3\2"+
		"\2\2\7,\3\2\2\2\t.\3\2\2\2\13<\3\2\2\2\r>\3\2\2\2\17@\3\2\2\2\21H\3\2"+
		"\2\2\23N\3\2\2\2\25S\3\2\2\2\27V\3\2\2\2\31X\3\2\2\2\33^\3\2\2\2\35`\3"+
		"\2\2\2\37c\3\2\2\2!f\3\2\2\2#j\3\2\2\2%n\3\2\2\2\'(\7/\2\2()\7@\2\2)\4"+
		"\3\2\2\2*+\7+\2\2+\6\3\2\2\2,-\7.\2\2-\b\3\2\2\2./\7T\2\2/\60\7g\2\2\60"+
		"\61\7c\2\2\61\62\7e\2\2\62\63\7v\2\2\63\64\7k\2\2\64\65\7x\2\2\65\66\7"+
		"g\2\2\66\67\7T\2\2\678\7w\2\289\7n\2\29:\7g\2\2:;\7u\2\2;\n\3\2\2\2<="+
		"\7<\2\2=\f\3\2\2\2>?\7*\2\2?\16\3\2\2\2@A\7H\2\2AB\7n\2\2BC\7w\2\2CD\7"+
		"g\2\2DE\7p\2\2EF\7v\2\2FG\7u\2\2G\20\3\2\2\2HI\7c\2\2IJ\7c\2\2JK\7c\2"+
		"\2KL\7c\2\2LM\7c\2\2M\22\3\2\2\2NO\7F\2\2OP\7U\2\2PQ\7g\2\2QR\7v\2\2R"+
		"\24\3\2\2\2ST\7~\2\2TU\7~\2\2U\26\3\2\2\2VW\7}\2\2W\30\3\2\2\2XY\7I\2"+
		"\2YZ\7q\2\2Z[\7c\2\2[\\\7n\2\2\\]\7u\2\2]\32\3\2\2\2^_\7\177\2\2_\34\3"+
		"\2\2\2`a\7>\2\2ab\7/\2\2b\36\3\2\2\2cd\7\60\2\2d \3\2\2\2eg\t\2\2\2fe"+
		"\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\"\3\2\2\2jk\7#\2\2kl\5!\21\2l"+
		"$\3\2\2\2mo\t\3\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2"+
		"rs\b\23\2\2s&\3\2\2\2\5\2hp";
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