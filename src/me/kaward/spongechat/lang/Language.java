/**
 * 	Spongechat, a new powered chat system for SpongePowered Minecraft API.
 * 	Copyright (C) 2015 Kaward <https://github.com/Kaward/>
 * 	Copyright (C) 2015 SparkPowered <https://github.com/SparkPowered/>
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.kaward.spongechat.lang;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

/**
 *
 * <p>
 * This class contains the language items and language factory.
 * </p>
 *
 * @category Language Handlering
 * @see me.kaward.spongechat.lang.Lang The "@Lang" Annotation
 *
 */
public class Language
{

	@Lang(name = "You dont writed message", id = 1, group = 1, keywords = { "YDWM" })
	public static String YOU_DONT_WRITED_MESSAGE = Language.getDefaultString(1);

	@Lang(name = "You leaved from the channel", id = 2, group = 2, keywords = { "YLFTC" })
	public static String YOU_LEAVED_FROM_THE_CHANNEL = Language.getDefaultString(2);

	@Lang(name = "You entered into the channel", id = 3, group = 2, keywords = { "YEITC" })
	public static String YOU_ENTERED_INTO_THE_CHANNEL = Language.getDefaultString(3);

	@Lang(name = "You are muted from the channel", id = 4, group = 3, keywords = { "YAMFTC" })
	public static String YOU_ARE_MUTED_FROM_THE_CHANNEL = Language.getDefaultString(4);

	@Lang(name = "You are unmuted from the channel", id = 5, group = 3, keywords = { "YAUFTC" })
	public static String YOU_ARE_UNMUTED_FROM_THE_CHANNEL = Language.getDefaultString(5);

	@Lang(name = "You are muted from all channels", id = 6, group = 3, keywords = { "YAMFAC" })
	public static String YOU_ARE_MUTED_FROM_ALL_CHANNELS = Language.getDefaultString(6);

	@Lang(name = "You are unmuted from all channels", id = 7, group = 3, keywords = { "YAUFAC" })
	public static String YOU_ARE_UNMUTED_FROM_ALL_CHANNELS = Language.getDefaultString(7);

	@Lang(name = "You are muted from this channel", id = 8, group = 3, keywords = { "YAMFTHC" })
	public static String YOU_ARE_MUTED_FROM_THIS_CHANNEL = Language.getDefaultString(8);

	@Lang(name = "Channel is muted", id = 9, group = 4, keywords = { "CIM" })
	public static String CHANNEL_IS_MUTED = Language.getDefaultString(9);

	public static String CHANNEL_CREATED_SUCCESSFULLY = null;
	public static String CHANNEL_REMOVED_SUCCESSFUULY = null;

	public static String CHANNEL_NOT_EXISTS = null;

	public static String getDefaultString(int i)
	{
		assert(i > 0) : "";
		switch (i)
		{
			case 1:
			{
				return "§6[Spongechat] §eYou don't writed the message.";
			}

			case 2:
			{
				return "§6[Spongechat] §eYou leaved from the channel.";
			}

			case 3:
			{
				return "§6[Spongechat] §eYou entered into the channel.";
			}

			case 4:
			{
				return "§6[Spongechat] §eYou are muted from the channel @channel.";
			}

			case 5:
			{
				return "§6[Spongechat] §eYou are unmuted from the channel @channel.";
			}

			case 6:
			{
				return "§6[Spongechat] §eYou are muted in all channels.";
			}

			case 7:
			{
				return "§6[Spongechat] §eYou are unmuted from all channels.";
			}

			case 8:
			{
				return "§6[Spongechat] §eYou are muted from this channel.";
			}

			default:
			{
				return "Invalid default string query.";
			}
		}
	}

	public static Text getDefaultText(int i)
	{
		switch (i)
		{
			case 1:
			{
				return Texts.of("§6[Spongechat] §eYou don't writed the message.");
			}

			case 2:
			{
				return Texts.of("§6[Spongechat] §eYou leaved from the channel.");
			}

			case 3:
			{
				return Texts.of("§6[Spongechat] §eYou entered into the channel.");
			}

			case 4:
			{
				return Texts.of("§6[Spongechat] §eYou are muted from the channel @channel.");
			}

			case 5:
			{
				return Texts.of("§6[Spongechat] §eYou are unmuted from the channel @channel.");
			}

			case 6:
			{
				return Texts.of("§6[Spongechat] §eYou are muted in all channels.");
			}

			case 7:
			{
				return Texts.of("§6[Spongechat] §eYou are unmuted from all channels.");
			}

			default:
			{
				return Texts.of("Invalid default text query.");
			}
		}
	}

}
