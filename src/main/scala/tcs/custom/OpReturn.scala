package tcs.custom

import tcs.utils.{ConvertUtils, RC4}

/**
  * Created by Livio on 14/06/2017.
  */
object OpReturn {
  def getApplication(key: String, metadata: String): String = {
    if (!metadata.contains(Codes.BRACKET)) {
      return Protocol.EMPTY;
    }

    if (metadata.contains(Codes.BRACKET + Codes.ASCRIBE_CODE1)) {
      return Protocol.ASCRIBE;
    }

    if (metadata.contains(Codes.BRACKET + Codes.BITALIAS_CODE1)) {
      return Protocol.BITALIAS;
    }

    if (metadata.contains(Codes.BRACKET + Codes.BITPROOF_CODE1)) {
      return Protocol.BITPROOF;
    }

    if (metadata.contains(Codes.BRACKET + Codes.BLOCKAI_CODE1)) {
      return Protocol.BLOCKAI;
    }

    if (metadata.contains(Codes.BRACKET + Codes.BLOCKSIGN_CODE1)) {
      return Protocol.BLOCKSIGN;
    }

    if (metadata.contains(Codes.BRACKET + Codes.BLOCKSTORE_CODE1) ||
      metadata.contains(Codes.BRACKET + Codes.BLOCKSTORE_CODE2) ||
      metadata.contains(Codes.BRACKET + Codes.BLOCKSTORE_CODE3)) {
      return Protocol.BLOCKSTORE;
    }

    if (metadata.contains(Codes.BRACKET + Codes.COINSPARK_CODE1)) {
      return Protocol.COINSPARK;
    }

    if (metadata.contains(Codes.BRACKET + Codes.COLU_CODE1)) {
      return Protocol.COLU;
    }

    if (metadata.contains(Codes.BRACKET + Codes.COUNTERPARTY_CODE1)) {
      return Protocol.COUNTERPARTY;
    }

    if (metadata.contains(Codes.BRACKET + Codes.CRYPTOCOPYRIGHT_CODE1) ||
      metadata.contains(Codes.BRACKET + Codes.CRYPTOCOPYRIGHT_CODE2)) {
      return Protocol.CRYPTOCOPYRIGHT;
    }

    if (metadata.contains(Codes.BRACKET + Codes.ETERNITYWALL_CODE1)) {
      return Protocol.ETERNITYWALL;
    }

    if (metadata.contains(Codes.BRACKET + Codes.FACTOM_CODE1) ||
      metadata.contains(Codes.BRACKET + Codes.FACTOM_CODE2) ||
      metadata.contains(Codes.BRACKET + Codes.FACTOM_CODE3) ||
      metadata.contains(Codes.BRACKET + Codes.FACTOM_CODE4)) {
      return Protocol.FACTOM;
    }

    if (metadata.contains(Codes.BRACKET + Codes.HELPERBIT_CODE1)) {
      return Protocol.HELPERBIT;
    }

    if (metadata.contains(Codes.BRACKET + Codes.LAPREUVE_CODE1)) {
      return Protocol.LAPREUVE;
    }

    if (metadata.contains(Codes.BRACKET + Codes.MONEGRAPH_CODE1)) {
      return Protocol.MONEGRAPH;
    }

    if (metadata.contains(Codes.BRACKET + Codes.NICOSIA_CODE1)) {
      return Protocol.NICOSIA;
    }

    if (metadata.contains(Codes.BRACKET + Codes.NOTARY_CODE1)) {
      return Protocol.NOTARY;
    }

    if (metadata.contains(Codes.BRACKET + Codes.OMNI_CODE1)) {
      return Protocol.OMNI;
    }

    if (metadata.contains(Codes.BRACKET + Codes.OPENASSETS_CODE1)) {
      return Protocol.OPENASSETS;
    }

    if (metadata.contains(Codes.BRACKET + Codes.OPENCHAIN_CODE1)) {
      return Protocol.OPENCHAIN;
    }

    if (metadata.contains(Codes.BRACKET + Codes.ORIGINALMY_CODE1)) {
      return Protocol.ORIGINALMY;
    }

    if (metadata.contains(Codes.BRACKET + Codes.PROOFOFEXISTENCE_CODE1)) {
      return Protocol.PROOFOFEXISTENCE;
    }

    if (metadata.contains(Codes.BRACKET + Codes.PROVEBIT_CODE1)) {
      return Protocol.PROVEBIT;
    }

    if (metadata.contains(Codes.BRACKET + Codes.REMEMBR_CODE1) ||
      metadata.contains(Codes.BRACKET + Codes.REMEMBR_CODE2)) {
      return Protocol.REMEMBR;
    }

    if (metadata.contains(Codes.BRACKET + Codes.SMARTBIT_CODE1)) {
      return Protocol.SMARTBIT;
    }

    if (metadata.contains(Codes.BRACKET + Codes.STAMPD_CODE1)) {
      return Protocol.STAMPD;
    }

    if (metadata.contains(Codes.BRACKET + Codes.STAMPERY_CODE1) ||
      metadata.contains(Codes.BRACKET + Codes.STAMPERY_CODE2) ||
      metadata.contains(Codes.BRACKET + Codes.STAMPERY_CODE3) ||
      metadata.contains(Codes.BRACKET + Codes.STAMPERY_CODE4) ||
      metadata.contains(Codes.BRACKET + Codes.STAMPERY_CODE5)) {
      return Protocol.STAMPERY;
    }

    val message = extractData(metadata)
    val rc4 = new RC4(ConvertUtils.hexToBytes(key))
    val result : String = ConvertUtils.bytesToHex(rc4.decrypt(ConvertUtils.hexToBytes(message))).toLowerCase

    if (result.startsWith(Codes.COUNTERPARTY_CODE1)) {
      return Protocol.COUNTERPARTY
    }

    return Protocol.UNKNOWN;
  }


  def extractData(message: String): String = {
    var v1: Integer = message.indexOf("[")
    var v2: Integer = message.indexOf("]")
    if ((v1 == -1) || (v2 == -1)) {
      return ""
    }
    else
      return message.substring(v1 + 1, v2)
  }
}
