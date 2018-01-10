/*
 * Copyright (c) 2018 Stanislaw stasbar Baranski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *          __             __
 *    _____/ /_____ ______/ /_  ____ ______
 *   / ___/ __/ __ `/ ___/ __ \/ __ `/ ___/
 *  (__  ) /_/ /_/ (__  ) /_/ / /_/ / /
 * /____/\__/\__,_/____/_.___/\__,_/_/
 *            taxledger@stasbar.com
 */

package com.stasbar.taxledger

import com.stasbar.taxledger.exceptions.ApiNotSetException
import com.stasbar.taxledger.exceptions.CredentialsException
import com.stasbar.taxledger.exceptions.TooManyCredentialsException
import com.stasbar.taxledger.exchanges.abucoins.AbuApi
import com.stasbar.taxledger.exchanges.bitbay.BitBayApi
import com.stasbar.taxledger.exchanges.bitmarket.BitmarketApi
import com.stasbar.taxledger.exchanges.coinroom.CoinroomApi
import org.fusesource.jansi.Ansi
import java.io.PrintWriter

object BitBay : Exchange<BitBayApi>("BitBay", "bb", listOf("publicKey", "privateKey"), Ansi.Color.BLUE) {


    private const val PUBLIC_KEY_LENGTH = 36
    private const val PRIVATE_KEY_LENGTH = 36

    var publicKey: String = ""
    var privateKey: String = ""

    @Throws(CredentialsException::class, TooManyCredentialsException::class)
    override fun addCredential(credential: String) {
        if (publicKey.isBlank()) {
            if (credential.length != PUBLIC_KEY_LENGTH)
                throw CredentialsException("publicKey", name, PUBLIC_KEY_LENGTH)
            else publicKey = credential
        } else if (privateKey.isBlank()) {
            if (credential.length != PRIVATE_KEY_LENGTH)
                throw CredentialsException("privateKey", name, PRIVATE_KEY_LENGTH)
            else privateKey = credential
        } else throw TooManyCredentialsException(name)

    }

    private var api: BitBayApi? = null

    @Throws(ApiNotSetException::class)
    override fun getApi(): BitBayApi {
        return if (api == null) {
            if (isSet().not())
                throw ApiNotSetException(name)
            else {
                api = BitBayApi(publicKey, privateKey)
                api!!
            }

        } else api!!
    }

    override fun isSet() = publicKey.isNotBlank() && privateKey.isNotBlank()

    override fun printCredentials(writer: PrintWriter) {
        writer.println("bitbay")
        writer.println(publicKey)
        writer.println(privateKey)
        writer.flush()
    }

}

object Abucoins : Exchange<AbuApi>("Abucoins", "abu", listOf("passphrase", "key", "secret"), Ansi.Color.GREEN) {
    private const val KEY_LENGTH = 41
    private const val SECRET_LENGTH = 64
    private const val PASSPHRASE_LENGTH = 8

    var passphrase: String = ""
    var key: String = ""
    var secret: String = ""


    @Throws(CredentialsException::class, TooManyCredentialsException::class)
    override fun addCredential(credential: String) {
        if (passphrase.isBlank()) {
            if (credential.length < PASSPHRASE_LENGTH)
                throw CredentialsException("passphrase", name, PASSPHRASE_LENGTH)
            else passphrase = credential
        } else if (key.isBlank()) {
            if (credential.length != KEY_LENGTH)
                throw CredentialsException("key", name, KEY_LENGTH)
            else key = credential
        } else if (secret.isBlank()) {
            if (credential.length != SECRET_LENGTH)
                throw CredentialsException("secret", name, SECRET_LENGTH)
            else secret = credential

        } else throw TooManyCredentialsException(name)

    }

    private var api: AbuApi? = null

    @Throws(ApiNotSetException::class)
    override fun getApi(): AbuApi {
        return if (api == null) {
            if (isSet().not())
                throw ApiNotSetException(name)
            else {
                api = AbuApi(key, secret, passphrase)
                api!!
            }

        } else api!!
    }

    override fun isSet() = key.isNotBlank() && secret.isNotBlank() && passphrase.isNotBlank()

    override fun printCredentials(writer: PrintWriter) {
        writer.println("abu")
        writer.println(passphrase)
        writer.println(key)
        writer.println(secret)
        writer.flush()
    }

}

object BitMarket : Exchange<BitmarketApi>("Bitmarket", "bm", listOf("publicKey", "privateKey"), Ansi.Color.BLUE) {


    private const val PUBLIC_KEY_LENGTH = 32
    private const val PRIVATE_KEY_LENGTH = 32

    var publicKey: String = ""
    var privateKey: String = ""

    @Throws(CredentialsException::class, TooManyCredentialsException::class)
    override fun addCredential(credential: String) {
        if (publicKey.isBlank()) {
            if (credential.length != PUBLIC_KEY_LENGTH)
                throw CredentialsException("publicKey", name, PUBLIC_KEY_LENGTH)
            else publicKey = credential
        } else if (privateKey.isBlank()) {
            if (credential.length != PRIVATE_KEY_LENGTH)
                throw CredentialsException("privateKey", name, PRIVATE_KEY_LENGTH)
            else privateKey = credential
        } else throw TooManyCredentialsException(name)

    }

    private var api: BitmarketApi? = null

    @Throws(ApiNotSetException::class)
    override fun getApi(): BitmarketApi {
        return if (api == null) {
            if (isSet().not())
                throw ApiNotSetException(name)
            else {
                api = BitmarketApi(publicKey, privateKey)
                api!!
            }

        } else api!!
    }

    override fun isSet() = publicKey.isNotBlank() && privateKey.isNotBlank()

    override fun printCredentials(writer: PrintWriter) {
        writer.println("bitmarket")
        writer.println(publicKey)
        writer.println(privateKey)
        writer.flush()
    }

}

object Coinroom : Exchange<CoinroomApi>("Coinroom", "cr", listOf("publicKey", "privateKey"), Ansi.Color.YELLOW) {


    private const val PUBLIC_KEY_LENGTH = 36
    private const val PRIVATE_KEY_LENGTH = 36

    var publicKey: String = ""
    var privateKey: String = ""

    @Throws(CredentialsException::class, TooManyCredentialsException::class)
    override fun addCredential(credential: String) {
        if (publicKey.isBlank()) {
            if (credential.length != PUBLIC_KEY_LENGTH)
                throw CredentialsException("publicKey", name, PUBLIC_KEY_LENGTH)
            else publicKey = credential
        } else if (privateKey.isBlank()) {
            if (credential.length != PRIVATE_KEY_LENGTH)
                throw CredentialsException("privateKey", name, PRIVATE_KEY_LENGTH)
            else privateKey = credential
        } else throw TooManyCredentialsException(name)

    }

    private var api: CoinroomApi? = null

    @Throws(ApiNotSetException::class)
    override fun getApi(): CoinroomApi {
        return if (api == null) {
            if (isSet().not())
                throw ApiNotSetException(name)
            else {
                api = CoinroomApi(publicKey, privateKey)
                api!!
            }

        } else api!!
    }

    override fun isSet() = publicKey.isNotBlank() && privateKey.isNotBlank()

    override fun printCredentials(writer: PrintWriter) {
        writer.println("coinroom")
        writer.println(publicKey)
        writer.println(privateKey)
        writer.flush()
    }

}

abstract class Exchange<out ApiType : ExchangeApi>(val name: String, val shortcut: String, val credentialsSteps: List<String>, val color: Ansi.Color) {
    @Throws(ApiNotSetException::class)
    abstract fun getApi(): ApiType

    @Throws(CredentialsException::class, TooManyCredentialsException::class)
    abstract fun addCredential(credential: String)

    fun isNameOf(candidate: String) = candidate.toLowerCase() in arrayOf(name.toLowerCase(), shortcut.toLowerCase())
    abstract fun isSet(): Boolean
    abstract fun printCredentials(writer: PrintWriter)
}
